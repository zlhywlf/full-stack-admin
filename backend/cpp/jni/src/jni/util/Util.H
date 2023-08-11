#pragma once

#include <string>
#include <vector>

#include "file/Util.H"
#include "jni.h"
#include "json/util/Util.H"

namespace zlhywlf {
namespace jni {
namespace Util {
struct Method {
  int id;
  std::string name;
  std::string sign;
  std::string description;

  ZLHYWLF_JSON_REGISTER_KF(id, name, sign, description)
};

struct Clazz {
  std::string clazz;
  std::vector<Method> methods;
  std::string description;

  ZLHYWLF_JSON_REGISTER_KF(clazz, methods, description)
};

struct Conf {
  std::vector<Clazz> mappers;

  ZLHYWLF_JSON_REGISTER_KF(mappers)
};

jint jniOnLoadLinkLibrary(JavaVM *vm, void *reserved, void **func,
                          const std::string &confPath) {
  JNIEnv *env;
  if (JNI_OK != vm->GetEnv(reinterpret_cast<void **>(&env), JNI_VERSION_1_8)) {
    return JNI_ERR;
  }
  jclass logClazz = env->FindClass("org/slf4j/LoggerFactory");
  if (logClazz) {
    jmethodID id = env->GetStaticMethodID(
        logClazz, "getLogger", "(Ljava/lang/String;)Lorg/slf4j/Logger;");
    jobject logObj = env->CallStaticObjectMethod(
        logClazz, id, env->NewStringUTF("nativeMethod"));
    jclass clz = env->GetObjectClass(logObj);
    jmethodID infoId = env->GetMethodID(clz, "info", "(Ljava/lang/String;)V");
    env->CallVoidMethod(logObj, infoId, env->NewStringUTF("hello java!"));
  }
  std::string confStr;
  file::Util::readFileAsString(confPath, confStr);
  Conf conf;
  json::Util::readValue(conf, confStr);
  for (auto &&d : conf.mappers) {
    std::size_t size = d.methods.size();
    if (size != (sizeof(func) / sizeof(func[0]))) {
      return JNI_ERR;
    }
    jclass clazz = env->FindClass(d.clazz.c_str());
    JNINativeMethod methods[size];
    for (std::size_t i = 0; i < size; i++) {
      Method &m = d.methods[i];
      methods[i] = {m.name.data(), m.sign.data(), func[m.id]};
    }
    jint res = env->RegisterNatives(clazz, methods, size);
    if (res != JNI_OK) {
      return JNI_ERR;
    }
  }
  return JNI_VERSION_1_8;
}

#ifndef ZLHYWLF_JNI_NAME
#define ZLHYWLF_JNI_NAME(name) "/usr/java/packages/mapper/" #name ".json"
#endif

#ifndef ZLHYWLF_JNI_FUNC_ARR
#define ZLHYWLF_JNI_FUNC_ARR(...) \
  { __VA_ARGS__ }
#endif

#ifndef ZLHYWLF_JNI_FUNC
#define ZLHYWLF_JNI_FUNC(f) reinterpret_cast<void *>(f)
#endif

#ifndef ZLHYWLF_JNI_EXPORT_ONLOAD
#define ZLHYWLF_JNI_EXPORT_ONLOAD(path, funcArr)                               \
  JNIEXPORT jint JNICALL JNI_OnLoad(JavaVM *vm, void *reserved) {              \
    void *func[] = funcArr;                                                    \
    return zlhywlf::jni::Util::jniOnLoadLinkLibrary(vm, reserved, func, path); \
  }
#endif
}  // namespace Util
}  // namespace jni
}  // namespace zlhywlf