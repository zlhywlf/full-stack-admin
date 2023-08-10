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
inline void readValue(const std::string &key, T &obj,
                      const RapidjsonModel &model,
                      const std::function<bool(const RapidjsonModel &)> &func);

template <typename T>
inline void writeValueAsString(
    const std::string &key, const T &obj, RapidjsonModel &model,
    const std::function<void(RapidjsonModel &)> &func);
/******************************************************
 *
 * 未知类型
 *
 ******************************************************/
template <typename T,
          typename std::enable_if_t<!hasNecessaryMethod<T>::has, int> = 0>
inline void readValue(const std::string &key, T &obj,
                      const RapidjsonModel &model);

template <typename T,
          typename std::enable_if_t<!hasNecessaryMethod<T>::has, int> = 0>
inline void writeValueAsString(const std::string &key, const T &obj,
                               RapidjsonModel &model);

/******************************************************
 *
 * class
 *
 ******************************************************/
template <typename T,
          typename std::enable_if_t<hasNecessaryMethod<T>::has, int> = 0>
inline void readValue(const std::string &key, T &obj,
                      const RapidjsonModel &model);

template <typename T,
          typename std::enable_if_t<hasNecessaryMethod<T>::has, int> = 0>
inline void writeValueAsString(const std::string &key, const T &obj,
                               RapidjsonModel &model);

/******************************************************
 *
 * int
 *
 ******************************************************/
template <>
inline void readValue(const std::string &key, int &obj,
                      const RapidjsonModel &model);

template <>
inline void writeValueAsString(const std::string &key, const int &obj,
                               RapidjsonModel &model);
}  // namespace Mapper
}  // namespace json
}  // namespace zlhywlf

namespace zlhywlf {
namespace json {
namespace Mapper {
template <typename T>
inline void readValue(const std::string &key, T &obj,
                      const RapidjsonModel &model,
                      const std::function<bool(const RapidjsonModel &)> &func) {
  if (!model.value) {
    model.log.error("编码错误");
    return;
  }
  if (model.value->IsNull()) {
    model.log.warn("字段 " + key + " json 值为 null");
    return;
  }
  // 根节点
  if (key.empty()) {
    func(model);
    return;
  }
  RapidjsonModel sub;
  auto k = key.c_str();
  if (model.value->HasMember(k)) {
    sub.value = &model.value->operator[](k);
  } else {
    model.log.error("字段 " + key + " 不存在于 json 中");
    return;
  }
  if (!func(sub)) {
    std::string actual;
    switch (sub.value->GetType()) {
      case ::rapidjson::Type::kNullType:
        actual = "null";
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
        "[类型不匹配] 字段 " + key + " 类型为: " +
        abi::__cxa_demangle(typeid(obj).name(), nullptr, nullptr, nullptr) +
        ", json 类型为: " + actual);
  }
}

template <typename T>
inline void writeValueAsString(
    const std::string &key, const T &obj, RapidjsonModel &model,
    const std::function<void(RapidjsonModel &)> &func) {
  if (!model.value || !model.allocator) {
    model.log.error("编码错误");
    return;
  }
  // 根节点
  if (key.empty()) {
    func(model);
    return;
  }
  if (model.value->HasMember(key.c_str())) {
    model.value->RemoveMember(key.c_str());
  }
  rapidjson::Value item;
  RapidjsonModel sub;
  sub.value = &item;
  sub.allocator = model.allocator;
  func(sub);
  rapidjson::Value k;
  k.SetString(key.c_str(), key.length(), *model.allocator);
  model.value->AddMember(k, item, *model.allocator);
}

template <typename T,
          typename std::enable_if_t<!hasNecessaryMethod<T>::has, int>>
inline void readValue(const std::string &key, T &obj,
                      const RapidjsonModel &model) {
  model.log.warn(
      "读取字段 " + key + " 失败, 无类型 " +
      abi::__cxa_demangle(typeid(obj).name(), nullptr, nullptr, nullptr) +
      " 的映射规则, 被忽略");
}

template <typename T,
          typename std::enable_if_t<!hasNecessaryMethod<T>::has, int>>
inline void writeValueAsString(const std::string &key, const T &obj,
                               RapidjsonModel &model) {
  model.log.warn(
      "写出字段 " + key + " 失败, 无类型 " +
      abi::__cxa_demangle(typeid(obj).name(), nullptr, nullptr, nullptr) +
      " 的映射规则, 被忽略");
}

template <typename T,
          typename std::enable_if_t<hasNecessaryMethod<T>::has, int>>
inline void readValue(const std::string &key, T &obj,
                      const RapidjsonModel &model) {
  readValue(key, obj, model, [&](const RapidjsonModel &sub) {
    if (sub.value->IsObject()) {
      std::vector<std::string> keysVector;
      obj.readValue(sub, keysVector);
      return true;
    }
    return false;
  });
}

template <typename T,
          typename std::enable_if_t<hasNecessaryMethod<T>::has, int>>
inline void writeValueAsString(const std::string &key, const T &obj,
                               RapidjsonModel &model) {
  writeValueAsString(key, obj, model, [&](RapidjsonModel &sub) {
    sub.value->SetObject();
    std::vector<std::string> keysVector;
    obj.writeValueAsString(sub, keysVector);
  });
}

template <>
inline void readValue(const std::string &key, int &obj,
                      const RapidjsonModel &model) {
  readValue(key, obj, model, [&](const RapidjsonModel &sub) {
    if (sub.value->IsInt()) {
      obj = sub.value->GetInt();
      return true;
    }
    return false;
  });
}

template <>
inline void writeValueAsString(const std::string &key, const int &obj,
                               RapidjsonModel &model) {
  writeValueAsString(key, obj, model,
                     [&](RapidjsonModel &sub) { sub.value->SetInt(obj); });
}
}  // namespace Mapper
}  // namespace json
}  // namespace zlhywlf