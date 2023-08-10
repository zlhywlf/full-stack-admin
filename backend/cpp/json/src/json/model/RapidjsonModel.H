#pragma once

#include "Model.H"
#include "rapidjson/document.h"

namespace zlhywlf {
namespace json {
struct RapidjsonModel : public Model<RapidjsonModel> {
  rapidjson::Value *value;
  rapidjson::Document::AllocatorType *allocator;
};
}  // namespace json
}  // namespace zlhywlf