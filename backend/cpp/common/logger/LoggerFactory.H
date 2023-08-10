#pragma once

#include "DefaultLoggerAdapter.H"
#include "Logger.H"
#include "singleton/Singleton.H"

namespace zlhywlf {
namespace logger {
namespace LoggerFactory {
const Logger &createLogger() {
  return singleton::Singleton<DefaultLoggerAdapter>::getInstance();
}
}  // namespace LoggerFactory
}  // namespace logger
}  // namespace zlhywlf