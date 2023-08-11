#include "jniTest.cpp"

#include <iostream>

#include "jni.h"

using std::cout;
using std::endl;

int main(int argc, char const *argv[]) {
  JavaVM *jvm;
  JNIEnv *env;
  JavaVMInitArgs vmArgs;
  vmArgs.version = JNI_VERSION_20;
  vmArgs.nOptions = 0;
  vmArgs.ignoreUnrecognized = JNI_TRUE;
  JNI_CreateJavaVM(&jvm, reinterpret_cast<void **>(&env), &vmArgs);
  jstring res = cpp(env, nullptr, env->NewStringUTF("java"));
  cout << endl << env->GetStringUTFChars(res, JNI_FALSE) << endl;
  jvm->DestroyJavaVM();
  return 0;
}