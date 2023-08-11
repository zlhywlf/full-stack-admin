#include <iostream>

#include "jni.h"

using std::cout;
using std::endl;

jstring cpp(JNIEnv *env, jobject obj, jstring jsonStr) {
  cout << "from java: " << env->GetStringUTFChars(jsonStr, JNI_FALSE);
  return env->NewStringUTF("hello java!-- 动态链接");
}