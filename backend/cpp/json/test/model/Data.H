#pragma once

#include "json/util/JsonUtil.H"

struct Sub {
  int i;

  ZLHYWLF_JSON_REGISTER_KF(i)
};

struct Data {
  int i{0};
  double d;
  Sub sub;

  ZLHYWLF_JSON_REGISTER_KF(i, d, sub)
};