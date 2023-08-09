#pragma once

#include "DefaultLoggerAdapter.H"
#include "LoggerFactory.H"

namespace zlhywlf {
namespace logger {
class DefaultLoggerFactory : public LoggerFactory {
 public:
  virtual Logger *createLogger() const override;
};

Logger *DefaultLoggerFactory::createLogger() const {
  return new DefaultLoggerAdapter();
}
}  // namespace logger
}  // namespace zlhywlf