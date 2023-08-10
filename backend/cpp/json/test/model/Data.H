#pragma once

#include "json/util/Util.H"

enum E { ONE, TWO };

struct Sub {
  int i;

  ZLHYWLF_JSON_REGISTER_KF(i)
};

struct Data {
  int i{0};
  double d;
  Sub sub;
  E e;

  ZLHYWLF_JSON_REGISTER_KF(i, d, sub, e)
};