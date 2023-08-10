#pragma once

#include <cxxabi.h>

#include <functional>
#include <string>
#include <vector>

#include "SFINAE.H"
#include "json/model/RapidjsonModel.H"

namespace zlhywlf {
namespace json {
namespace Mapper {
template <typename T>
inline void readValue(T &obj, const RapidjsonModel &model,
                      const std::function<bool()> &func);

template <typename T>
inline void writeValueAsString2(
    const T &obj, RapidjsonModel &model,
    const std::function<void(RapidjsonModel &)> &func);
/******************************************************
 *
 * 未知类型
 *
 ******************************************************/
template <typename T,
          typename std::enable_if_t<!hasNecessaryMethod<T>::has, int> = 0>
inline void readValue(T &obj, const RapidjsonModel &model);

template <typename T,
          typename std::enable_if_t<!hasNecessaryMethod<T>::has, int> = 0>
inline void writeValueAsString(const T &obj, RapidjsonModel &model);

/******************************************************
 *
 * class
 *
 ******************************************************/
template <typename T,
          typename std::enable_if_t<hasNecessaryMethod<T>::has, int> = 0>
inline void readValue(T &obj, const RapidjsonModel &model);

template <typename T,
          typename std::enable_if_t<hasNecessaryMethod<T>::has, int> = 0>
inline void writeValueAsString(const T &obj, RapidjsonModel &model);

/******************************************************
 *
 * int
 *
 ******************************************************/
template <>
inline void readValue(int &obj, const RapidjsonModel &model);

template <>
inline void writeValueAsString(const int &obj, RapidjsonModel &model);
}  // namespace Mapper
}  // namespace json
}  // namespace zlhywlf

namespace zlhywlf {
namespace json {
namespace Mapper {
template <typename T>
inline void readValue(T &obj, const RapidjsonModel &model,
                      const std::function<bool()> &func) {
  if (!model.value) {
    model.log.error(model.name + " 值不存在");
    return;
  }
  if (model.value->IsNull()) {
    model.log.warn(model.name + " 值为 null");
    return;
  }
  if (!func()) {
    std::string actual;
    switch (model.value->GetType()) {
      case ::rapidjson::Type::kNullType:
        actual = "Null";
        break;
      case ::rapidjson::Type::kFalseType:
      case ::rapidjson::Type::kTrueType:
        actual = "bool";
        break;
      case ::rapidjson::Type::kObjectType:
        actual = "object";
        break;
      case ::rapidjson::Type::kArrayType:
        actual = "array";
        break;
      case ::rapidjson::Type::kStringType:
        actual = "string";
        break;
      case ::rapidjson::Type::kNumberType:
        actual = "number";
        break;
      default:
        actual = "未知类型";
    }
    model.log.error(
        "字段" + model.name + ": 类型不匹配, json 类型为: " + actual +
        ", 期待的类型为: " +
        abi::__cxa_demangle(typeid(obj).name(), nullptr, nullptr, nullptr));
  }
}

template <typename T>
inline void writeValueAsString2(
    const T &obj, RapidjsonModel &model,
    const std::function<void(RapidjsonModel &)> &func) {
  // 根节点
  if (model.name.empty()) {
    func(model);
    return;
  }
  if (!model.value || !model.allocator) {
    model.log.error(model.name + " 写入对象不存在");
    return;
  }
  rapidjson::Value item;
  RapidjsonModel sub;
  sub.value = &item;
  sub.allocator = model.allocator;
  func(sub);
  rapidjson::Value key;
  key.SetString(model.name.c_str(), model.name.length(), *model.allocator);
  model.value->AddMember(key, item, *model.allocator);
}

template <typename T,
          typename std::enable_if_t<!hasNecessaryMethod<T>::has, int>>
inline void readValue(T &obj, const RapidjsonModel &model) {
  readValue(obj, model, [&]() { return false; });
}

template <typename T,
          typename std::enable_if_t<!hasNecessaryMethod<T>::has, int>>
inline void writeValueAsString(const T &obj, RapidjsonModel &model) {
  // 未知类型
}

template <typename T,
          typename std::enable_if_t<hasNecessaryMethod<T>::has, int>>
inline void readValue(T &obj, const RapidjsonModel &model) {
  readValue(obj, model, [&]() {
    if (model.value && model.value->IsObject()) {
      std::vector<std::string> keysVector;
      obj.readValue(model, keysVector);
      return true;
    }
    return false;
  });
}

template <typename T,
          typename std::enable_if_t<hasNecessaryMethod<T>::has, int>>
inline void writeValueAsString(const T &obj, RapidjsonModel &model) {
  writeValueAsString2(obj, model, [&](RapidjsonModel &sub) {
    sub.value->SetObject();
    std::vector<std::string> keysVector;
    obj.writeValueAsString(sub, keysVector);
  });
}

template <>
inline void readValue(int &obj, const RapidjsonModel &model) {
  readValue(obj, model, [&]() {
    if (model.value->IsInt()) {
      obj = model.value->GetInt();
      return true;
    }
    return false;
  });
}

template <>
inline void writeValueAsString(const int &obj, RapidjsonModel &model) {
  writeValueAsString2(obj, model,
                      [&](RapidjsonModel &sub) { sub.value->SetInt(obj); });
}
}  // namespace Mapper
}  // namespace json
}  // namespace zlhywlf