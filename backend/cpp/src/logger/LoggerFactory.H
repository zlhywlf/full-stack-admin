#pragma once

#include "Logger.H"

namespace zlhywlf {
namespace logger {
class LoggerFactory {
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
  virtual ~LoggerFactory() = default;

  /******************************************************
   *
   * methods
   *
   ******************************************************/
 public:
  virtual Logger *createLogger() const = 0;
};
}  // namespace logger
}  // namespace zlhywlf