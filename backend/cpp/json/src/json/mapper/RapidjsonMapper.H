#pragma once

#include <cxxabi.h>

#include <functional>
#include <list>
#include <map>
#include <string>
#include <vector>

#include "SFINAE.H"
#include "json/model/RapidjsonModel.H"
#include "rapidjson/stringbuffer.h"
#include "rapidjson/writer.h"

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
          typename std::enable_if_t<
              !hasNecessaryMethod<T>::has && !std::is_enum_v<T>, int> = 0>
inline void readValue(const std::string &key, T &obj,
                      const RapidjsonModel &model);

template <typename T,
          typename std::enable_if_t<
              !hasNecessaryMethod<T>::has && !std::is_enum_v<T>, int> = 0>
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
 * enum
 *
 ******************************************************/
template <typename T, typename std::enable_if_t<std::is_enum_v<T>, int> = 0>
inline void readValue(const std::string &key, T &obj,
                      const RapidjsonModel &model);

template <typename T, typename std::enable_if_t<std::is_enum_v<T>, int> = 0>
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

/******************************************************
 *
 * double
 *
 ******************************************************/
template <>
inline void readValue(const std::string &key, double &obj,
                      const RapidjsonModel &model);

template <>
inline void writeValueAsString(const std::string &key, const double &obj,
                               RapidjsonModel &model);

/******************************************************
 *
 * bool
 *
 ******************************************************/
template <>
inline void readValue(const std::string &key, bool &obj,
                      const RapidjsonModel &model);

template <>
inline void writeValueAsString(const std::string &key, const bool &obj,
                               RapidjsonModel &model);

/******************************************************
 *
 * int64
 *
 ******************************************************/
template <>
inline void readValue(const std::string &key, std::int64_t &obj,
                      const RapidjsonModel &model);

template <>
inline void writeValueAsString(const std::string &key, const std::int64_t &obj,
                               RapidjsonModel &model);

/******************************************************
 *
 * list
 *
 ******************************************************/
template <typename T>
inline void readValue(const std::string &key, std::list<T> &obj,
                      const RapidjsonModel &model);

template <typename T>
inline void writeValueAsString(const std::string &key, const std::list<T> &obj,
                               RapidjsonModel &model);

/******************************************************
 *
 * map
 *
 ******************************************************/
template <typename T>
inline void readValue(const std::string &key, std::map<std::string, T> &obj,
                      const RapidjsonModel &model);

template <typename T>
inline void writeValueAsString(const std::string &key,
                               const std::map<std::string, T> &obj,
                               RapidjsonModel &model);

/******************************************************
 *
 * string
 *
 ******************************************************/
template <>
inline void readValue(const std::string &key, std::string &obj,
                      const RapidjsonModel &model);

template <>
inline void writeValueAsString(const std::string &key, const std::string &obj,
                               RapidjsonModel &model);

/******************************************************
 *
 * uint
 *
 ******************************************************/
template <>
inline void readValue(const std::string &key, unsigned int &obj,
                      const RapidjsonModel &model);

template <>
inline void writeValueAsString(const std::string &key, const unsigned int &obj,
                               RapidjsonModel &model);

/******************************************************
 *
 * uint64
 *
 ******************************************************/
template <>
inline void readValue(const std::string &key, std::uint64_t &obj,
                      const RapidjsonModel &model);

template <>
inline void writeValueAsString(const std::string &key, const std::uint64_t &obj,
                               RapidjsonModel &model);

/******************************************************
 *
 * vector
 *
 ******************************************************/
template <typename T>
inline void readValue(const std::string &key, std::vector<T> &obj,
                      const RapidjsonModel &model);

template <typename T>
inline void writeValueAsString(const std::string &key,
                               const std::vector<T> &obj,
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
  // 匿名节点: 根节点, 列表元素
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
  if (sub.value->IsNull()) {
    model.log.warn("字段 " + key + " json 值为 null");
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
  // 匿名节点: 根节点, 列表元素
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

/******************************************************
 *
 * 未知类型
 *
 ******************************************************/
template <typename T,
          typename std::enable_if_t<
              !hasNecessaryMethod<T>::has && !std::is_enum_v<T>, int>>
inline void readValue(const std::string &key, T &obj,
                      const RapidjsonModel &model) {
  model.log.warn(
      "读取字段 " + key + " 失败, 无类型 " +
      abi::__cxa_demangle(typeid(obj).name(), nullptr, nullptr, nullptr) +
      " 的映射规则, 被忽略");
}

template <typename T,
          typename std::enable_if_t<
              !hasNecessaryMethod<T>::has && !std::is_enum_v<T>, int>>
inline void writeValueAsString(const std::string &key, const T &obj,
                               RapidjsonModel &model) {
  model.log.warn(
      "写出字段 " + key + " 失败, 无类型 " +
      abi::__cxa_demangle(typeid(obj).name(), nullptr, nullptr, nullptr) +
      " 的映射规则, 被忽略");
}

/******************************************************
 *
 * class
 *
 ******************************************************/
template <typename T,
          typename std::enable_if_t<hasNecessaryMethod<T>::has, int>>
inline void readValue(const std::string &key, T &obj,
                      const RapidjsonModel &model) {
  readValue(key, obj, model, [&](const RapidjsonModel &sub) {
    if (sub.value->IsObject()) {
      std::vector<std::string> keyVector;
      obj.readValue(sub, keyVector);
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
    std::vector<std::string> keyVector;
    obj.writeValueAsString(sub, keyVector);
  });
}

/******************************************************
 *
 * enum
 *
 ******************************************************/
template <typename T, typename std::enable_if_t<std::is_enum_v<T>, int>>
inline void readValue(const std::string &key, T &obj,
                      const RapidjsonModel &model) {
  readValue(key, obj, model, [&](const RapidjsonModel &sub) {
    if (sub.value->IsInt()) {
      obj = static_cast<T>(sub.value->GetInt());
      return true;
    }
    return false;
  });
}

template <typename T, typename std::enable_if_t<std::is_enum_v<T>, int>>
inline void writeValueAsString(const std::string &key, const T &obj,
                               RapidjsonModel &model) {
  writeValueAsString(key, obj, model, [&](RapidjsonModel &sub) {
    sub.value->SetInt(static_cast<int>(obj));
  });
}

/******************************************************
 *
 * int
 *
 ******************************************************/
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

/******************************************************
 *
 * double
 *
 ******************************************************/
template <>
inline void readValue(const std::string &key, double &obj,
                      const RapidjsonModel &model) {
  readValue(key, obj, model, [&](const RapidjsonModel &sub) {
    if (sub.value->IsNumber()) {
      obj = sub.value->GetDouble();
      return true;
    }
    return false;
  });
}

template <>
inline void writeValueAsString(const std::string &key, const double &obj,
                               RapidjsonModel &model) {
  writeValueAsString(key, obj, model,
                     [&](RapidjsonModel &sub) { sub.value->SetDouble(obj); });
}

/******************************************************
 *
 * bool
 *
 ******************************************************/
template <>
inline void readValue(const std::string &key, bool &obj,
                      const RapidjsonModel &model) {
  readValue(key, obj, model, [&](const RapidjsonModel &sub) {
    if (sub.value->IsBool()) {
      obj = sub.value->GetBool();
      return true;
    }
    return false;
  });
}

template <>
inline void writeValueAsString(const std::string &key, const bool &obj,
                               RapidjsonModel &model) {
  writeValueAsString(key, obj, model,
                     [&](RapidjsonModel &sub) { sub.value->SetBool(obj); });
}

/******************************************************
 *
 * int64
 *
 ******************************************************/
template <>
inline void readValue(const std::string &key, std::int64_t &obj,
                      const RapidjsonModel &model) {
  readValue(key, obj, model, [&](const RapidjsonModel &sub) {
    if (sub.value->IsInt64()) {
      obj = sub.value->GetInt64();
      return true;
    }
    return false;
  });
}

template <>
inline void writeValueAsString(const std::string &key, const std::int64_t &obj,
                               RapidjsonModel &model) {
  writeValueAsString(key, obj, model,
                     [&](RapidjsonModel &sub) { sub.value->SetInt64(obj); });
}

/******************************************************
 *
 * list
 *
 ******************************************************/
template <typename T>
inline void readValue(const std::string &key, std::list<T> &obj,
                      const RapidjsonModel &model) {
  readValue(key, obj, model, [&](const RapidjsonModel &sub) {
    if (sub.value->IsArray()) {
      obj.clear();
      auto values = sub.value->GetArray();
      RapidjsonModel e;
      for (auto &&v : values) {
        T o;
        e.value = &v;
        readValue("", o, e);
        obj.push_back(o);
      }
      return true;
    }
    return false;
  });
}

template <typename T>
inline void writeValueAsString(const std::string &key, const std::list<T> &obj,
                               RapidjsonModel &model) {
  writeValueAsString(key, obj, model, [&](RapidjsonModel &sub) {
    auto &value = *sub.value;
    if (obj.size() > 0) {
      value.SetArray();
      rapidjson::Value v;
      sub.value = &v;
      for (auto i = obj.begin(); i != obj.end(); i++) {
        writeValueAsString("", *i, sub);
        value.PushBack(*sub.value, *sub.allocator);
      }
      return;
    }
    value.SetNull();
  });
}

/******************************************************
 *
 * map
 *
 ******************************************************/
template <typename T>
inline void readValue(const std::string &key, std::map<std::string, T> &obj,
                      const RapidjsonModel &model) {
  readValue(key, obj, model, [&](const RapidjsonModel &sub) {
    if (sub.value->IsObject()) {
      obj.clear();
      for (auto iter = sub.value->MemberBegin(); iter != sub.value->MemberEnd();
           iter++) {
        auto k = (iter->name).GetString();
        T v;
        readValue(k, v, sub);
        obj.insert(std::make_pair(k, v));
      }
      return true;
    }
    return false;
  });
}

template <typename T>
inline void writeValueAsString(const std::string &key,
                               const std::map<std::string, T> &obj,
                               RapidjsonModel &model) {
  writeValueAsString(key, obj, model, [&](RapidjsonModel &sub) {
    if (obj.size() > 0) {
      sub.value->SetObject();
      for (auto iter = obj.begin(); iter != obj.end(); ++iter) {
        auto k = iter->first;
        T v = iter->second;
        writeValueAsString(k, v, sub);
      }
      return;
    }
    sub.value->SetNull();
  });
}

/******************************************************
 *
 * string
 *
 ******************************************************/
template <>
inline void readValue(const std::string &key, std::string &obj,
                      const RapidjsonModel &model) {
  readValue(key, obj, model, [&](const RapidjsonModel &sub) {
    if (sub.value->IsString()) {
      obj = sub.value->GetString();
      return true;
    }
    if (sub.value->IsObject() || sub.value->IsNumber() ||
        sub.value->IsArray() || sub.value->IsBool()) {
      rapidjson::StringBuffer buffer;
      rapidjson::Writer<rapidjson::StringBuffer> writer(buffer);
      sub.value->Accept(writer);
      obj = buffer.GetString();
      return true;
    }
    return false;
  });
}

template <>
inline void writeValueAsString(const std::string &key, const std::string &obj,
                               RapidjsonModel &model) {
  writeValueAsString(key, obj, model, [&](RapidjsonModel &sub) {
    if (obj.length() > 0) {
      sub.value->SetString(obj.c_str(), obj.length(), *sub.allocator);
      return;
    }
    sub.value->SetNull();
  });
}

/******************************************************
 *
 * uint
 *
 ******************************************************/
template <>
inline void readValue(const std::string &key, unsigned int &obj,
                      const RapidjsonModel &model) {
  readValue(key, obj, model, [&](const RapidjsonModel &sub) {
    if (sub.value->IsUint()) {
      obj = sub.value->GetUint();
      return true;
    }
    return false;
  });
}

template <>
inline void writeValueAsString(const std::string &key, const unsigned int &obj,
                               RapidjsonModel &model) {
  writeValueAsString(key, obj, model,
                     [&](RapidjsonModel &sub) { sub.value->SetUint(obj); });
}

/******************************************************
 *
 * uint64
 *
 ******************************************************/
template <>
inline void readValue(const std::string &key, std::uint64_t &obj,
                      const RapidjsonModel &model) {
  readValue(key, obj, model, [&](const RapidjsonModel &sub) {
    if (sub.value->IsUint64()) {
      obj = sub.value->GetUint64();
      return true;
    }
    return false;
  });
}

template <>
inline void writeValueAsString(const std::string &key, const std::uint64_t &obj,
                               RapidjsonModel &model) {
  writeValueAsString(key, obj, model,
                     [&](RapidjsonModel &sub) { sub.value->SetUint64(obj); });
}

/******************************************************
 *
 * vector
 *
 ******************************************************/
template <typename T>
inline void readValue(const std::string &key, std::vector<T> &obj,
                      const RapidjsonModel &model) {
  readValue(key, obj, model, [&](const RapidjsonModel &sub) {
    if (sub.value->IsArray()) {
      obj.clear();
      auto values = sub.value->GetArray();
      RapidjsonModel e;
      for (auto &&v : values) {
        T o;
        e.value = &v;
        readValue("", o, e);
        obj.push_back(o);
      }
      return true;
    }
    return false;
  });
}

template <typename T>
inline void writeValueAsString(const std::string &key,
                               const std::vector<T> &obj,
                               RapidjsonModel &model) {
  writeValueAsString(key, obj, model, [&](RapidjsonModel &sub) {
    auto &value = *sub.value;
    if (obj.size() > 0) {
      value.SetArray();
      rapidjson::Value v;
      sub.value = &v;
      for (auto i = obj.begin(); i != obj.end(); i++) {
        writeValueAsString("", *i, sub);
        value.PushBack(*sub.value, *sub.allocator);
      }
      return;
    }
    value.SetNull();
  });
}
}  // namespace Mapper
}  // namespace json
}  // namespace zlhywlf