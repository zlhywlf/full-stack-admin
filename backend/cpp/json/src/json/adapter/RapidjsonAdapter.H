#pragma once

#include "json/mapper/Mapper.H"
#include "json/model/RapidjsonModel.H"
#include "rapidjson/document.h"
#include "rapidjson/stringbuffer.h"
#include "rapidjson/writer.h"

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
  rapidjson::Document *doc_;
  RapidjsonModel model_;

  /******************************************************
   *
   * constructors
   *
   ******************************************************/
 public:
  RapidjsonAdapter();

  /******************************************************
   *
   * destructor
   *
   ******************************************************/
 public:
  ~RapidjsonAdapter();

  /******************************************************
   *
   * methods
   *
   ******************************************************/
 public:
  template <typename T>
  void readValue(T &obj, const std::string &jsonStr);

  template <typename T>
  void writeValueAsString(const T &obj, std::string &jsonStr);
};

RapidjsonAdapter::RapidjsonAdapter() : doc_(new rapidjson::Document) {
  model_.value = doc_;
}

RapidjsonAdapter::~RapidjsonAdapter() {
  if (doc_) {
    delete doc_;
    doc_ = nullptr;
  }
}

template <typename T>
void RapidjsonAdapter::readValue(T &obj, const std::string &jsonStr) {
  if (doc_->Parse(jsonStr.c_str()).HasParseError()) {
    model_.log.error("json 格式不正确");
    return;
  }
  Mapper::readValue("", obj, model_);
}

template <typename T>
void RapidjsonAdapter::writeValueAsString(const T &obj, std::string &jsonStr) {
  model_.allocator = &doc_->GetAllocator();
  Mapper::writeValueAsString("", obj, model_);
  rapidjson::StringBuffer buffer;
  rapidjson::Writer<::rapidjson::StringBuffer> writer(buffer);
  doc_->Accept(writer);
  jsonStr = buffer.GetString();
}
}  // namespace json
}  // namespace zlhywlf