#pragma once

#include "Adapter.H"
#include "json/mapper/Mapper.H"
#include "json/model/RapidjsonModel.H"
#include "logger/LoggerFactory.H"
#include "rapidjson/document.h"

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
  const logger::Logger &log_;
  RapidjsonModel model_;

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
   * @param log 日志
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
  void writeValueAsString(const T &obj);
};

RapidjsonAdapter::RapidjsonAdapter(const std::string &jsonStr)
    : Adapter(jsonStr),
      doc_(new rapidjson::Document),
      log_(logger::LoggerFactory::createLogger()) {
  doc_->Parse(jsonStr.c_str());
  model_.value = doc_;
}

RapidjsonAdapter::~RapidjsonAdapter() {
  if (doc_ != nullptr) {
    delete doc_;
    doc_ = nullptr;
  }
}

template <typename T>
void RapidjsonAdapter::readValue(T &obj) {
  Mapper::readValue(obj, model_);
}

template <typename T>
void RapidjsonAdapter::writeValueAsString(const T &obj) {}
}  // namespace json
}  // namespace zlhywlf