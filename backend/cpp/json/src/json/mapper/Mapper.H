#pragma once

#include <vector>

#include "RapidjsonMapper.H"

namespace zlhywlf {
namespace json {
namespace Mapper {
template <typename T, typename F, typename... FF>
void readValue(const T& model, const std::vector<std::string>& keysVector,
               int keyIndex, F& field) {
  readValue(keysVector[keyIndex], field, model);
}

template <typename T, typename F, typename... FF>
void readValue(const T& model, const std::vector<std::string>& keysVector,
               int keyIndex, F& field, FF&... fields) {
  readValue(model, keysVector, keyIndex, field);
  readValue(model, keysVector, ++keyIndex, fields...);
}

template <typename T, typename F, typename... FF>
void writeValueAsString(T& model, const std::vector<std::string> keysVector,
                        int keyIndex, const F& field) {
  writeValueAsString(keysVector[keyIndex], field, model);
}

template <typename T, typename F, typename... FF>
void writeValueAsString(T& model, const std::vector<std::string> keysVector,
                        int keyIndex, const F& field, const FF&... fields) {
  writeValueAsString(model, keysVector, keyIndex, field);
  writeValueAsString(model, keysVector, ++keyIndex, fields...);
}
}  // namespace Mapper
}  // namespace json
}  // namespace zlhywlf