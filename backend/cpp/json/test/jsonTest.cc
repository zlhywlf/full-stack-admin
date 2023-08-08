#include <iostream>

#include "rapidjson/document.h"

using std::cout;
using std::endl;

int main(int argc, char const *argv[]) {
  const char *json =
      R"({"hello":"world","t":true,"n":null,"i":123,"pi":3.1415926,"a":[0,1,2,3]})";
  rapidjson::Document doc;
  doc.Parse(json);
  assert(doc.HasMember("hello"));
  return 0;
}