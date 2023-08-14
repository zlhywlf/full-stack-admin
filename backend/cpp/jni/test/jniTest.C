#include "jni.h"
#include "logger/LoggerFactory.H"

#ifdef __cplusplus
extern "C" {
#endif
jstring Java_zlhywlf_jni_demo_Demo_run(JNIEnv *env, jobject obj,
                                       jstring jsonStr) {
  auto &log = zlhywlf::logger::LoggerFactory::createLogger();
  log.info("from java: " +
           std::string(env->GetStringUTFChars(jsonStr, JNI_FALSE)));
  return env->NewStringUTF("hello java!-- 静态链接");
}
#ifdef __cplusplus
}
#endif