#pragma once

#include "logger/LoggerFactory.H"
#include "rapidjson/document.h"

namespace zlhywlf {
namespace json {
struct RapidjsonModel {
  rapidjson::Value *value{nullptr};
  rapidjson::Document::AllocatorType *allocator{nullptr};
  const logger::Logger &log{logger::LoggerFactory::createLogger()};
};
}  // namespace json
}  // namespace zlhywlf