#include <iostream>

#include "jni.h"

using std::cout;
using std::endl;

#ifdef __cplusplus
extern "C" {
#endif
jstring Java_zlhywlf_jni_demo_Demo_run(JNIEnv *env, jobject obj,
                                       jstring jsonStr) {
  cout << "from java: " << env->GetStringUTFChars(jsonStr, JNI_FALSE);
  return env->NewStringUTF("hello java!-- 静态链接");
}
#ifdef __cplusplus
}
#endif