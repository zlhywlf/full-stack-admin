#pragma once

#include "DefaultLoggerAdapter.H"
#include "Logger.H"

namespace zlhywlf {
namespace logger {
class LoggerFactory final {
  /******************************************************
   *
   * constructors
   *
   ******************************************************/
 public:
  LoggerFactory() = default;
  LoggerFactory(const LoggerFactory &) = delete;

  /******************************************************
   *
   * operators
   *
   ******************************************************/
 public:
  LoggerFactory &operator=(const LoggerFactory &) = delete;

  /******************************************************
   *
   * destructor
   *
   ******************************************************/
 public:
  ~LoggerFactory() = default;

  /******************************************************
   *
   * methods
   *
   ******************************************************/
 public:
  Logger *createLogger() const;
};

Logger *LoggerFactory::createLogger() const {
  return new DefaultLoggerAdapter();
}
}  // namespace logger
}  // namespace zlhywlf