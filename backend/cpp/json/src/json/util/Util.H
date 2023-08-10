#pragma once

#include "json/adapter/RapidjsonAdapter.H"
#include "json/mapper/Mapper.H"
#include "string/Util.H"

namespace zlhywlf {
namespace json {
namespace Util {
template <typename T, typename K = RapidjsonAdapter>
inline void readValue(T& obj, const std::string& jsonStr) {
  K adapter;
  adapter.readValue(obj, jsonStr);
}

template <typename T, typename K = RapidjsonAdapter>
inline void writeValueAsString(const T& obj, std::string& jsonStr) {
  K adapter;
  adapter.writeValueAsString(obj, jsonStr);
}

#ifndef ZLHYWLF_JSON_K
#define ZLHYWLF_JSON_K(...) #__VA_ARGS__
#endif

#ifndef ZLHYWLF_JSON_F
#define ZLHYWLF_JSON_F(...) __VA_ARGS__
#endif

#ifndef ZLHYWLF_JSON_REGISTER
#define ZLHYWLF_JSON_REGISTER(keys, fields)                                    \
  const char* serializedKey() const { return keys; }                           \
  template <typename T>                                                        \
  void readValue(const T& model, std::vector<std::string>& keysVector) {       \
    zlhywlf::string::Util::splitStrToVector(keysVector, serializedKey(), ','); \
    zlhywlf::json::Mapper::readValue(model, keysVector, 0, fields);            \
  }                                                                            \
  template <typename T>                                                        \
  void writeValueAsString(T& model, std::vector<std::string>& keysVector)      \
      const {                                                                  \
    zlhywlf::string::Util::splitStrToVector(keysVector, serializedKey(), ','); \
    zlhywlf::json::Mapper::writeValueAsString(model, keysVector, 0, fields);   \
  }
#endif

#ifndef ZLHYWLF_JSON_REGISTER_KF
#define ZLHYWLF_JSON_REGISTER_KF(...)                \
  ZLHYWLF_JSON_REGISTER(ZLHYWLF_JSON_K(__VA_ARGS__), \
                        ZLHYWLF_JSON_F(__VA_ARGS__))
#endif

};  // namespace Util
}  // namespace json
}  // namespace zlhywlf