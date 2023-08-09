#pragma once

#include "json/adapter/Adapter.H"
#include "json/adapter/RapidjsonAdapter.H"

namespace zlhywlf {
namespace json {
namespace JsonUtil {
template <typename T, typename K = RapidjsonAdapter>
void readValue(T& obj, const std::string& jsonStr) {
  Adapter<K>* adapter = new K(jsonStr);
  adapter->readValue(obj);
  delete static_cast<K*>(adapter);
}
};  // namespace JsonUtil
}  // namespace json
}  // namespace zlhywlf