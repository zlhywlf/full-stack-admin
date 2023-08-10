#pragma once

#include "logger/Logger.H"
#include "rapidjson/document.h"

namespace zlhywlf {
namespace json {
struct RapidjsonModel {
  rapidjson::Value *value;
  rapidjson::Document::AllocatorType *allocator;
  logger::Logger *log;

  RapidjsonModel operator[](const char *key) {
    // todo 封装到类
    RapidjsonModel sub;
    if (value->HasMember(key)) {
      sub.value = &value->operator[](key);
      sub.allocator = allocator;
    }
    return sub;
  }
};
}  // namespace json
}  // namespace zlhywlf