#pragma once

#include "Model.H"
#include "logger/LoggerFactory.H"
#include "rapidjson/document.h"

namespace zlhywlf {
namespace json {
struct RapidjsonModel : public Model<RapidjsonModel> {
  rapidjson::Value *value;
  rapidjson::Document::AllocatorType *allocator;
  std::string name;
  const logger::Logger &log{logger::LoggerFactory::createLogger()};

  RapidjsonModel operator[](const std::string &key) const {
    RapidjsonModel sub;
    auto k = key.c_str();
    if (value->HasMember(k)) {
      sub.value = &value->operator[](k);
      sub.allocator = allocator;
      sub.name = key;
    } else {
      log.error("json 不存在成员:" + key);
    }
    return sub;
  }
};
}  // namespace json
}  // namespace zlhywlf