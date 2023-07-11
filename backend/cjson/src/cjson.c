#include "cjson.h"

#include <limits.h>
#include <string.h>

const size_t bufferSize = 256;

char *ensure(Buffer *buffer, size_t needed) {
  char *newStr;
  size_t newSize;
  // 偏移量 offset 应该在 buffer->len 以内
  if ((buffer->len > 0) && (buffer->offset >= buffer->len)) {
    return NULL;
  }
  // 需要的内存长度极限
  if (needed > INT_MAX) {
    return NULL;
  }
  // 计划 buffer->len 总量
  needed += buffer->offset + 1;
  // 无需扩容则返回当前偏移位置
  if (needed <= buffer->len) {
    return buffer->str + buffer->offset;
  }
  if (needed > (INT_MAX / 2)) {
    newSize = INT_MAX;
  } else {
    // 2 倍扩容
    newSize = needed * 2;
  }
  newStr = (char *)mem.reallocate(buffer->str, newSize);
  if (newStr == NULL) {
    mem.deallocate(buffer->str);
    buffer->len = 0;
    buffer->str = NULL;
    return NULL;
  }
  buffer->len = newSize;
  buffer->str = newStr;
  return newStr + buffer->offset;
}

void *newMem(size_t size) {
  void *p = mem.allocate(size);
  if (p) {
    memset(p, 0, size);
  }
  return p;
}

Json *newJson() { return (Json *)newMem(sizeof(Json)); }

Json *newItem(Type t) {
  Json *p = newJson();
  if (p) {
    p->t = t;
  }
  return p;
}

Json *newNullItem() { return newItem(J_NULL); }

Json *newFalseItem() { return newItem(J_FALSE); }

Json *newTrueItem() { return newItem(J_TRUE); }

Json *newNumberItem() { return newItem(J_NUMBER); }

Json *newStringItem() { return newItem(J_STRING); }

Json *newArrayItem() { return newItem(J_ARRAY); }

Json *newObjectItem() { return newItem(J_OBJECT); }

char *toString(Json *json) {
  // 根对象类型必须是 J_OBJECT
  if (!json || json->t ^ J_OBJECT) {
    return NULL;
  }
  Buffer buffer;
  memset(&buffer, 0, sizeof(Buffer));
  buffer.str = newMem(bufferSize);
  if (!buffer.str) {
    return NULL;
  }
  buffer.len = bufferSize;
  char *current = buffer.str;
  // 依次赋值
  *current++ = '{', buffer.offset++;
  if (json->v) {
    // TODO
  }
  *current++ = '}', buffer.offset++;
  // 去除多余的内存
  mem.reallocate(buffer.str, ++buffer.offset);
  return buffer.str;
}