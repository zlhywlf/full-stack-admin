#pragma once

#include "json/util/Util.H"

enum E { ONE, TWO };

struct Sub {
  int number;

  ZLHYWLF_JSON_REGISTER(ZLHYWLF_JSON_K(i), ZLHYWLF_JSON_F(number))
};

struct Data {
  int i{0};
  double d;
  Sub sub;
  E e;
  bool b;
  std::int64_t ll;
  std::list<int> ls;
  std::map<std::string, int> m;
  std::string s;
  unsigned int ui;
  std::uint64_t ull;
  std::vector<std::string> v;

  ZLHYWLF_JSON_REGISTER_KF(i, d, sub, e, b, ll, ls, m, s, ui, ull, v)
};