#pragma once

#include "Logger.H"
#include "LoggerServer.H"
#include "singleton/Singleton.H"

namespace zlhywlf {
namespace logger {
namespace LoggerFactory {
LoggerServer &createLogger() {
  return singleton::Singleton<LoggerServer>::getInstance();
}
}  // namespace LoggerFactory
}  // namespace logger
}  // namespace zlhywlf