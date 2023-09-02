#pragma once

#include <vector>

#include "RapidjsonMapper.H"

namespace zlhywlf {
namespace json {
namespace Mapper {
template <typename T, typename F, typename... FF>
void readValue(const T& model, const std::vector<std::string>& keyVector,
               int keyIndex, F& field) {
  readValue(keyVector[keyIndex], field, model);
}

template <typename T, typename F, typename... FF>
void readValue(const T& model, const std::vector<std::string>& keyVector,
               int keyIndex, F& field, FF&... fields) {
  readValue(model, keyVector, keyIndex, field);
  readValue(model, keyVector, ++keyIndex, fields...);
}

template <typename T, typename F, typename... FF>
void writeValueAsString(T& model, const std::vector<std::string> keyVector,
                        int keyIndex, const F& field) {
  writeValueAsString(keyVector[keyIndex], field, model);
}

template <typename T, typename F, typename... FF>
void writeValueAsString(T& model, const std::vector<std::string> keyVector,
                        int keyIndex, const F& field, const FF&... fields) {
  writeValueAsString(model, keyVector, keyIndex, field);
  writeValueAsString(model, keyVector, ++keyIndex, fields...);
}
}  // namespace Mapper
}  // namespace json
}  // namespace zlhywlf