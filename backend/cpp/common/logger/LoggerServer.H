#pragma once

#include "DefaultLoggerAdapter.H"
#include "Logger.H"
#include "jni.h"

namespace zlhywlf {
namespace jni {
namespace Util {
jint jniOnLoadLinkLibrary(JavaVM* vm, void* reserved, void** func,
                          const std::string& confPath);
}  // namespace Util
}  // namespace jni
namespace logger {
class LoggerServer : public Logger {
 private:
  Logger* log_;

 public:
  LoggerServer();

 public:
  virtual ~LoggerServer();

 public:
  virtual void trace(const std::string& msg) const override;
  virtual void debug(const std::string& msg) const override;
  virtual void info(const std::string& msg) const override;
  virtual void warn(const std::string& msg) const override;
  virtual void error(const std::string& msg) const override;

 public:
  friend jint jni::Util::jniOnLoadLinkLibrary(JavaVM* vm, void* reserved,
                                              void** func,
                                              const std::string& confPath);
};

LoggerServer::LoggerServer() : log_(new DefaultLoggerAdapter()) {}

LoggerServer::~LoggerServer() {
  if (log_) {
    delete log_;
    log_ = nullptr;
  }
}

inline void LoggerServer::trace(const std::string& msg) const {
  log_->trace(msg);
}

inline void LoggerServer::debug(const std::string& msg) const {
  log_->debug(msg);
}

inline void LoggerServer::info(const std::string& msg) const {
  log_->info(msg);
}

inline void LoggerServer::warn(const std::string& msg) const {
  log_->warn(msg);
}

inline void LoggerServer::error(const std::string& msg) const {
  log_->error(msg);
}
}  // namespace logger
}  // namespace zlhywlf