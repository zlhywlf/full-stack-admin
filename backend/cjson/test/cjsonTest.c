#include <stdio.h>

#include "cjson.h"

int main(int argc, char const* argv[]) {
  Json* strItem = newStringItem();
  Value* strV = newValue();
  strV->str = "hello world中文测试\"\\\b\f\n\r\t";
  strItem->v = strV;
  strItem->k = "\u628a\"\\\b\f\n\r\tstr";

  Json* strItem3 = newStringItem();
  Value* strV3 = newValue();
  strV3->str = "hello";
  // strItem3->v = strV3;
  strItem3->k = "str";

  strItem->next = strItem3;

  Json* root = newObjectItem();
  Value* rootV = newValue();
  rootV->child = strItem;
  root->v = rootV;
  char* str = toString(root);
  if (str) {
    printf("%s\n", str);
  }
  mem.deallocate(str);
  mem.deallocate(root);
  mem.deallocate(strItem);
  mem.deallocate(strV);
  mem.deallocate(rootV);
  return 0;
}
