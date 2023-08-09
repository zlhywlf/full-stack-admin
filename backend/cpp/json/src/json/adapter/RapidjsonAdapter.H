#pragma once

#include "Adapter.H"
#include "json/mapper/RapidjsonMapper.H"
#include "json/model/RapidjsonModel.H"
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
   * @param jsonStr factory 日志工厂
   */
  RapidjsonAdapter(const std::string &jsonStr,
                   const logger::LoggerFactory &factory);
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
   * methods
   *
   ******************************************************/
 public:
  template <typename T>
  void readValue(T &obj);

  template <typename T>
  void writeValueAsString(const T &obj);
};

RapidjsonAdapter::RapidjsonAdapter(const std::string &jsonStr,
                                   const logger::LoggerFactory &factory)
    : Adapter(jsonStr, factory), doc_(new rapidjson::Document) {
  doc_->Parse(jsonStr.c_str());
  model_.value = doc_;
  model_.log = log_;
}

RapidjsonAdapter::~RapidjsonAdapter() {
  if (doc_ != nullptr) {
    delete doc_;
    doc_ = nullptr;
  }
}

template <typename T>
void RapidjsonAdapter::readValue(T &obj) {
  RapidjsonMapper::readValue(obj, model_);
}

template <typename T>
void RapidjsonAdapter::writeValueAsString(const T &obj) {}
}  // namespace json
}  // namespace zlhywlf