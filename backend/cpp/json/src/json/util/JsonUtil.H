#pragma once

#include "json/adapter/Adapter.H"
#include "json/adapter/RapidjsonAdapter.H"
#include "json/mapper/IntMapper.H"
#include "json/model/RapidjsonModel.H"

namespace zlhywlf {
namespace json {
namespace JsonUtil {
template <typename T, typename K = RapidjsonAdapter,
          typename M = RapidjsonModel>
void readValue(T& obj, const std::string& jsonStr) {
  Adapter<K, M>* adapter = new K(jsonStr);
  IntMapper m;
  m.readValue(obj, adapter->model()["i"]);
  delete static_cast<K*>(adapter);
}
};  // namespace JsonUtil
}  // namespace json
}  // namespace zlhywlf