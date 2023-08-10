#pragma once

#include "Adapter.H"
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
class RapidjsonAdapter final : public Adapter<RapidjsonAdapter> {
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

  /**
   * @brief Construct a new Rapidjson Adapter object
   *
   * @param jsonStr json 字符串
   */
  RapidjsonAdapter(const std::string &jsonStr);

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
  void readValue(T &obj);

  template <typename T>
  void writeValueAsString(const T &obj, std::string &jsonStr);
};

RapidjsonAdapter::RapidjsonAdapter(const std::string &jsonStr)
    : Adapter(jsonStr), doc_(new rapidjson::Document) {
  doc_->Parse(jsonStr.c_str());
  model_.value = doc_;
}

RapidjsonAdapter::RapidjsonAdapter() : doc_(new rapidjson::Document) {
  model_.value = doc_;
  model_.allocator = &doc_->GetAllocator();
}

RapidjsonAdapter::~RapidjsonAdapter() {
  if (doc_ != nullptr) {
    delete doc_;
    doc_ = nullptr;
  }
}

template <typename T>
void RapidjsonAdapter::readValue(T &obj) {
  Mapper::readValue("", obj, model_);
}

template <typename T>
void RapidjsonAdapter::writeValueAsString(const T &obj, std::string &jsonStr) {
  Mapper::writeValueAsString("", obj, model_);
  rapidjson::StringBuffer buffer;
  rapidjson::Writer<::rapidjson::StringBuffer> writer(buffer);
  doc_->Accept(writer);
  jsonStr = buffer.GetString();
}
}  // namespace json
}  // namespace zlhywlf