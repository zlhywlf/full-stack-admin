#include <iostream>

#include "json/util/JsonUtil.H"
#include "model/Data.H"

using std::cout;
using std::endl;

int main(int argc, char const *argv[]) {
  const char *json =
      R"({"hello":"world","t":true,"n":null,"i":123,"pi":3.1415926,"a":[0,1,2,3]})";
  Data data;
  cout << data.serializedKey() << endl;
  zlhywlf::json::JsonUtil::readValue(data, json);
  assert(data.i == 123);
}