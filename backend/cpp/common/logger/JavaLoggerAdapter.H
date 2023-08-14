#pragma once

#include "Logger.H"
#include "jni.h"

namespace zlhywlf {
namespace logger {
class JavaLoggerAdapter : public Logger {
 private:
  JNIEnv* env_;
  jobject log_;
  jmethodID traceId_;
  jmethodID debugId_;
  jmethodID infoId_;
  jmethodID warnId_;
  jmethodID errorId_;

 public:
  JavaLoggerAdapter(JNIEnv* env, jobject obj);

 public:
  virtual void trace(const std::string& msg) const override;
  virtual void debug(const std::string& msg) const override;
  virtual void info(const std::string& msg) const override;
  virtual void warn(const std::string& msg) const override;
  virtual void error(const std::string& msg) const override;
};

JavaLoggerAdapter::JavaLoggerAdapter(JNIEnv* env, jobject log)
    : env_(env), log_(log) {
  jclass logCls = env->GetObjectClass(log_);
  traceId_ = env->GetMethodID(logCls, "trace", "(Ljava/lang/String;)V");
  debugId_ = env->GetMethodID(logCls, "debug", "(Ljava/lang/String;)V");
  infoId_ = env->GetMethodID(logCls, "info", "(Ljava/lang/String;)V");
  warnId_ = env->GetMethodID(logCls, "warn", "(Ljava/lang/String;)V");
  errorId_ = env->GetMethodID(logCls, "error", "(Ljava/lang/String;)V");
  env->DeleteLocalRef(logCls);
}

inline void JavaLoggerAdapter::trace(const std::string& msg) const {
  env_->CallVoidMethod(log_, traceId_, env_->NewStringUTF(msg.c_str()));
}

inline void JavaLoggerAdapter::debug(const std::string& msg) const {
  env_->CallVoidMethod(log_, debugId_, env_->NewStringUTF(msg.c_str()));
}

inline void JavaLoggerAdapter::info(const std::string& msg) const {
  env_->CallVoidMethod(log_, infoId_, env_->NewStringUTF(msg.c_str()));
}

inline void JavaLoggerAdapter::warn(const std::string& msg) const {
  env_->CallVoidMethod(log_, warnId_, env_->NewStringUTF(msg.c_str()));
}

inline void JavaLoggerAdapter::error(const std::string& msg) const {
  env_->CallVoidMethod(log_, errorId_, env_->NewStringUTF(msg.c_str()));
}

}  // namespace logger
}  // namespace zlhywlf