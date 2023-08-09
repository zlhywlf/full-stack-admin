#pragma once

#include <string>
#include <vector>

#include "Mapper.H"
#include "json/model/RapidjsonModel.H"

namespace zlhywlf {
namespace json {
namespace RapidjsonMapper {
/******************************************************
 *
 * 未知类型
 *
 ******************************************************/
template <typename T,
          typename std::enable_if_t<!hasNecessaryMethod<T>::has, int> = 0>
void readValue(T &obj, const RapidjsonModel &model);

template <typename T,
          typename std::enable_if_t<!hasNecessaryMethod<T>::has, int> = 0>
void writeValueAsString(const T &obj, RapidjsonModel &model);

/******************************************************
 *
 * class
 *
 ******************************************************/
template <typename T,
          typename std::enable_if_t<hasNecessaryMethod<T>::has, int> = 0>
void readValue(T &obj, const RapidjsonModel &model);

template <typename T,
          typename std::enable_if_t<hasNecessaryMethod<T>::has, int> = 0>
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
template <typename T,
          typename std::enable_if_t<!hasNecessaryMethod<T>::has, int>>
void readValue(T &obj, const RapidjsonModel &model) {
  // 未知类型
}

template <typename T,
          typename std::enable_if_t<!hasNecessaryMethod<T>::has, int>>
void writeValueAsString(const T &obj, RapidjsonModel &model) {
  // 未知类型
}

template <typename T,
          typename std::enable_if_t<hasNecessaryMethod<T>::has, int>>
void readValue(T &obj, const RapidjsonModel &model) {
  std::vector<std::string> keysVector;
  obj.readValue(model, keysVector, *model.log);
}

template <typename T,
          typename std::enable_if_t<hasNecessaryMethod<T>::has, int>>
void writeValueAsString(const T &obj, RapidjsonModel &model) {}

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