#include <iostream>

#include "jni/util/Util.H"

using std::cout;
using std::endl;

jstring cpp(JNIEnv *env, jobject obj, jstring jsonStr) {
  cout << "from java: " << env->GetStringUTFChars(jsonStr, JNI_FALSE);
  return env->NewStringUTF("hello java!-- 动态链接");
}
#include "config.h"
ZLHYWLF_JNI_EXPORT_ONLOAD(PATH, ZLHYWLF_JNI_FUNC_ARR(ZLHYWLF_JNI_FUNC(cpp)))