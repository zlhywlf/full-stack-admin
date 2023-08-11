#include <iostream>

#include "json/util/Util.H"
#include "model/Data.H"

using std::cout;
using std::endl;
using zlhywlf::json::Util::readValue;
using zlhywlf::json::Util::writeValueAsString;

int main(int argc, char const *argv[]) {
  const char *json =
      R"({"i":123,"d":3.1415926,"sub":{"i":99},"e":0,"b":true,"ll":999999999999999,"ls":[1,2],"m":{"i":11},"s":"string","ui":3029196080,"ull":140701862845600,"v":[1,"str",true,[2,"any"]]})";
  Data data;
  cout << "serializedKey: " << data.serializedKey() << endl;
  readValue(data, json);
  assert(data.i == 123);
  std::string js;
  writeValueAsString(data, js);
  cout << js << endl;
}