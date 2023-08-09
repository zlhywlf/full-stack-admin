#include <iostream>

#include "json/util/JsonUtil.H"

using std::cout;
using std::endl;

int main(int argc, char const *argv[]) {
  const char *json =
      R"({"hello":"world","t":true,"n":null,"i":123,"pi":3.1415926,"a":[0,1,2,3]})";
  int i;
  zlhywlf::json::JsonUtil::readValue(i, json);
  assert(i == 123);
  return 0;
}