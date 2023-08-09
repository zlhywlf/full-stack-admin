#pragma once

#include "Mapper.H"
#include "json/model/RapidjsonModel.H"

namespace zlhywlf {
namespace json {
class IntMapper : public Mapper<int, RapidjsonModel> {
 public:
  virtual void readValue(int &obj, const RapidjsonModel &model) override;

  virtual void writeValueAsString(const int &obj,
                                  RapidjsonModel &model) override;
};

void IntMapper::readValue(int &obj, const RapidjsonModel &model) {
  if (model.value && model.value->IsInt()) {
    obj = model.value->GetInt();
  }
}

void IntMapper::writeValueAsString(const int &obj, RapidjsonModel &model) {}
}  // namespace json
}  // namespace zlhywlf