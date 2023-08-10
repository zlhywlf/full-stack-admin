#include <iostream>

#include "json/util/Util.H"
#include "model/Data.H"

using std::cout;
using std::endl;
using zlhywlf::json::Util::readValue;
using zlhywlf::json::Util::writeValueAsString;

int main(int argc, char const *argv[]) {
  const char *json =
      R"({"hello":"world","t":true,"n":null,"i":123,"d":3.1415926,"a":[0,1,2,3],"sub":{"i":99}})";
  Data data;
  cout << data.serializedKey() << endl;
  readValue(data, json);
  assert(data.i == 123);
  std::string js;
  writeValueAsString(data, js);
  cout << js << endl;
}