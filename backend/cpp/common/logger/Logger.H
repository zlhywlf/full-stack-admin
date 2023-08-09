#pragma once

#include <string>

namespace zlhywlf {
namespace logger {
class Logger {
  /******************************************************
   *
   * constructors
   *
   ******************************************************/
 public:
  Logger() = default;
  Logger(const Logger&) = delete;

  /******************************************************
   *
   * operators
   *
   ******************************************************/
 public:
  Logger& operator=(const Logger&) = delete;

  /******************************************************
   *
   * destructor
   *
   ******************************************************/
 public:
  virtual ~Logger() = default;

  /******************************************************
   *
   * methods
   *
   ******************************************************/
 public:
  virtual void trace(const std::string& msg) const = 0;
  virtual void debug(const std::string& msg) const = 0;
  virtual void info(const std::string& msg) const = 0;
  virtual void warn(const std::string& msg) const = 0;
  virtual void error(const std::string& msg) const = 0;
};
}  // namespace logger
}  // namespace zlhywlf