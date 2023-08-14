
#include "jni/util/Util.H"
#include "logger/LoggerFactory.H"

jstring cpp(JNIEnv *env, jobject obj, jstring jsonStr) {
  auto &log = zlhywlf::logger::LoggerFactory::createLogger();
  log.info("from java: " +
           std::string(env->GetStringUTFChars(jsonStr, JNI_FALSE)));
  return env->NewStringUTF("hello java!-- 动态链接");
}
#include "config.h"
ZLHYWLF_JNI_EXPORT_ONLOAD(PATH, ZLHYWLF_JNI_FUNC_ARR(ZLHYWLF_JNI_FUNC(cpp)))