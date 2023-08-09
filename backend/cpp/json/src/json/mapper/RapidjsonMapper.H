#pragma once

#include "json/model/RapidjsonModel.H"

namespace zlhywlf {
namespace json {
namespace RapidjsonMapper {
/******************************************************
 *
 * 未知类型
 *
 ******************************************************/
template <typename T>
void readValue(T &obj, const RapidjsonModel &model);

template <typename T>
void writeValueAsString(const T &obj, RapidjsonModel &model);

/******************************************************
 *
 * int
 *
 ******************************************************/
template <>
void readValue(int &obj, const RapidjsonModel &model);

template <>
void writeValueAsString(const int &obj, RapidjsonModel &model);

// ------------------- implement -------------------
template <typename T>
void readValue(T &obj, const RapidjsonModel &model) {
  // 未知类型
}

template <typename T>
void writeValueAsString(const T &obj, RapidjsonModel &model) {
  // 未知类型
}

template <>
void readValue(int &obj, const RapidjsonModel &model) {
  if (model.value && model.value->IsInt()) {
    obj = model.value->GetInt();
  }
}

template <>
void writeValueAsString(const int &obj, RapidjsonModel &model) {}
}  // namespace RapidjsonMapper
}  // namespace json
}  // namespace zlhywlf