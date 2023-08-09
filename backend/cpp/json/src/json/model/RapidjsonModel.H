#pragma once

#include "rapidjson/document.h"

namespace zlhywlf {
namespace json {
struct RapidjsonModel {
  rapidjson::Value *value;
  rapidjson::Document::AllocatorType *allocator;

  RapidjsonModel operator[](const char *key) {
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