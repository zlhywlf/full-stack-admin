#pragma once

#include "logger/LoggerFactory.H"
#include "rapidjson/document.h"

namespace zlhywlf {
namespace json {
struct RapidjsonModel {
  rapidjson::Value *value;
  rapidjson::Document::AllocatorType *allocator;
  const logger::Logger &log{logger::LoggerFactory::createLogger()};
};
}  // namespace json
}  // namespace zlhywlf