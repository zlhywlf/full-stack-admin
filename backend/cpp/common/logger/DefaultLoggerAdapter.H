#pragma once

#include <iostream>

#include "Logger.H"

namespace zlhywlf {
namespace logger {
class DefaultLoggerAdapter : public Logger {
 public:
  virtual void trace(const std::string& msg) const override;
  virtual void debug(const std::string& msg) const override;
  virtual void info(const std::string& msg) const override;
  virtual void warn(const std::string& msg) const override;
  virtual void error(const std::string& msg) const override;
};

inline void DefaultLoggerAdapter::trace(const std::string& msg) const {
#ifndef NDEBUG
  std::cout << "TRACE\t" << msg << std::endl;
#endif
}

inline void DefaultLoggerAdapter::debug(const std::string& msg) const {
#ifndef NDEBUG
  std::cout << "DEBUG\t" << msg << std::endl;
#endif
}

inline void DefaultLoggerAdapter::info(const std::string& msg) const {
  std::cout << "INFO\t" << msg << std::endl;
}

inline void DefaultLoggerAdapter::warn(const std::string& msg) const {
  std::cout << "WARN\t" << msg << std::endl;
}

inline void DefaultLoggerAdapter::error(const std::string& msg) const {
  std::cout << "ERROR\t" << msg << std::endl;
}
}  // namespace logger
}  // namespace zlhywlf