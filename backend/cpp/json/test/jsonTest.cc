#include <iostream>

#include "mapper/RapidjsonAdapter.H"

using std::cout;
using std::endl;

int main(int argc, char const *argv[]) {
  const char *json =
      R"({"hello":"world","t":true,"n":null,"i":123,"pi":3.1415926,"a":[0,1,2,3]})";
  zlhywlf::json::RapidjsonAdapter adapter(json);
  assert(adapter.value().HasMember("hello"));
  return 0;
}