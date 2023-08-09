#pragma once

#include <string>

#include "rapidjson/document.h"

namespace zlhywlf {
namespace json {
/**
 * @brief 使用 rapidjson 处理 json
 *
 */
class RapidjsonAdapter final {
  /******************************************************
   *
   * fields
   *
   ******************************************************/
 private:
  rapidjson::Value *value_;
  rapidjson::Document::AllocatorType *allocator_;  // 指针无需释放

  /******************************************************
   *
   * constructors
   *
   ******************************************************/
 public:
  /**
   * @brief Construct a new Rapidjson Adapter object
   *
   * @param jsonStr json 字符串
   */
  RapidjsonAdapter(const std::string &jsonStr);
  RapidjsonAdapter(const RapidjsonAdapter &) = delete;

  /******************************************************
   *
   * operators
   *
   ******************************************************/
 public:
  RapidjsonAdapter &operator=(const RapidjsonAdapter &) = delete;

  /******************************************************
   *
   * destructor
   *
   ******************************************************/
 public:
  ~RapidjsonAdapter();

  /******************************************************
   *
   * getters and setters
   *
   ******************************************************/
 public:
  rapidjson::Value &value();
  const rapidjson::Value &value() const;
  rapidjson::Document::AllocatorType &allocator();
};

RapidjsonAdapter::RapidjsonAdapter(const std::string &jsonStr) {
  rapidjson::Document *doc = new rapidjson::Document;
  doc->Parse(jsonStr.c_str());
  value_ = doc;
}

RapidjsonAdapter::~RapidjsonAdapter() {
  if (value_ != nullptr) {
    delete value_;
  }
}

rapidjson::Value &RapidjsonAdapter::value() { return *value_; }

const rapidjson::Value &RapidjsonAdapter::value() const { return *value_; }

rapidjson::Document::AllocatorType &RapidjsonAdapter::allocator() {
  return *allocator_;
}
}  // namespace json
}  // namespace zlhywlf