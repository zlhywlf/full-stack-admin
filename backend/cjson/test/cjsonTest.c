#include <stdio.h>

#include "cjson.h"

int main(int argc, char const* argv[]) {
  Json* root = newObjectItem();
  char* str = toString(root);
  if (str) {
    printf("%s\n", str);
  }
  mem.deallocate(str);
  mem.deallocate(root);
  return 0;
}
