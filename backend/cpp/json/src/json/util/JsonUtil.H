#pragma once

#include <string>
#include <vector>

#include "json/adapter/Adapter.H"
#include "json/adapter/RapidjsonAdapter.H"
#include "json/mapper/Mapper.H"

namespace zlhywlf {
namespace json {
namespace JsonUtil {
template <typename T, typename K = RapidjsonAdapter>
void readValue(T& obj, const std::string& jsonStr) {
  Adapter<K>* adapter = new K(jsonStr);
  adapter->readValue(obj);
  delete static_cast<K*>(adapter);
}

template <typename T, typename K = RapidjsonAdapter>
void writeValueAsString(const T& obj, std::string& jsonStr) {
  Adapter<K>* adapter = new K();
  adapter->writeValueAsString(obj, jsonStr);
  delete static_cast<K*>(adapter);
}

/**
 * @brief 按指定分隔符拆分字符串到容器
 *
 * @param strVector 字符串容器
 * @param str 字符串
 * @param sep 分隔符
 */
void splitStrToVector(std::vector<std::string>& strVector,
                      const std::string& str, char sep) {
  std::string::size_type pos1(0);
  std::string::size_type pos2(str.find(sep));
  auto func = [](std::string& str) {
    if (!str.empty()) {
      str.erase(0, str.find_first_not_of(" "));
      str.erase(str.find_last_not_of(" ") + 1);
    }
  };
  while (std::string::npos != pos2) {
    auto sub(str.substr(pos1, pos2 - pos1));
    func(sub);
    strVector.push_back(sub);
    pos1 = pos2 + 1;
    pos2 = str.find(sep, pos1);
  }
  if (pos1 != str.length()) {
    auto sub(str.substr(pos1));
    func(sub);
    strVector.push_back(sub);
  }
}

#ifndef ZLHYWLF_JSON_K
#define ZLHYWLF_JSON_K(...) #__VA_ARGS__
#endif

#ifndef ZLHYWLF_JSON_F
#define ZLHYWLF_JSON_F(...) __VA_ARGS__
#endif

#ifndef ZLHYWLF_JSON_REGISTER
#define ZLHYWLF_JSON_REGISTER(keys, fields)                                  \
  const char* serializedKey() const { return keys; }                         \
  template <typename T>                                                      \
  void readValue(const T& model, std::vector<std::string>& keysVector) {     \
    zlhywlf::json::JsonUtil::splitStrToVector(keysVector, serializedKey(),   \
                                              ',');                          \
    zlhywlf::json::Mapper::readValue(model, keysVector, 0, fields);          \
  }                                                                          \
  template <typename T>                                                      \
  void writeValueAsString(T& model, std::vector<std::string>& keysVector)    \
      const {                                                                \
    zlhywlf::json::JsonUtil::splitStrToVector(keysVector, serializedKey(),   \
                                              ',');                          \
    zlhywlf::json::Mapper::writeValueAsString(model, keysVector, 0, fields); \
  }
#endif

#ifndef ZLHYWLF_JSON_REGISTER_KF
#define ZLHYWLF_JSON_REGISTER_KF(...)                \
  ZLHYWLF_JSON_REGISTER(ZLHYWLF_JSON_K(__VA_ARGS__), \
                        ZLHYWLF_JSON_F(__VA_ARGS__))
#endif

};  // namespace JsonUtil
}  // namespace json
}  // namespace zlhywlf