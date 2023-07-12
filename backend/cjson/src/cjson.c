#include "cjson.h"

#include <limits.h>
#include <stdio.h>
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

Value *newValue() { return (Value *)newMem(sizeof(Value)); }

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
  char *current;

  // 初始化栈
  Stack *stack = newStack();
  stack->json = json;
  Stack *pre;
  while (stack) {
    if (stack->json->k) {
      handleKey(&buffer, stack->json->k);
      addChar(&buffer, ':');
    }
    switch (stack->json->t) {
      case J_OBJECT:
        pre = stack;
        stack = handleObjectOrArray(stack, &buffer, '{', '}');
        if (pre != stack) {
          continue;
        }
        break;
      case J_ARRAY:
        pre = stack;
        stack = handleObjectOrArray(stack, &buffer, '[', ']');
        if (pre != stack) {
          continue;
        }
        break;
      case J_NULL:
        break;
      case J_FALSE:
        break;
      case J_NUMBER:
        break;
      case J_TRUE:
        break;
      case J_STRING:
        handleString(&buffer, stack->json->v->str);
        break;
      default:
        break;
    }
    Json *next = stack->json->next;
    // 栈顶元素处理完成后弹出
    stack = pop(stack);
    // 如果当前处理项存在下一项，将下一项入栈
    if (next) {
      addChar(&buffer, ',');
      stack = push(stack, next);
      continue;
    }
    // 将栈顶类型为 J_OBJECT 或 J_ARRAY 均出栈
    while (stack && (stack->json->t & J_OBJECT || stack->json->t & J_ARRAY)) {
      if (stack->json->t & J_OBJECT) {
        addChar(&buffer, '}');
        stack = pop(stack);
        continue;
      }
      if (stack->json->t & J_ARRAY) {
        addChar(&buffer, ']');
        stack = pop(stack);
      }
    }
    // 如果栈顶还有元素且 json 对象还存在下一项则添加逗号
    if (stack && stack->json->next) {
      addChar(&buffer, ',');
    }
  }
  // 去除多余的内存
  addChar(&buffer, '\0');
  mem.reallocate(buffer.str, buffer.offset);
  return buffer.str;
}

Stack *newStack() { return (Stack *)newMem(sizeof(Stack)); }

Stack *push(Stack *stack, Json *json) {
  Stack *s = newStack();
  s->next = stack;
  s->json = json;
  return s;
}

Stack *pop(Stack *stack) {
  Stack *next = stack->next;
  mem.deallocate(stack);
  return next;
}

Stack *handleObjectOrArray(Stack *stack, Buffer *buffer, char s, char e) {
  addChar(buffer, s);
  if (stack->json->v) {
    return push(stack, stack->json->v->child);
  }
  addChar(buffer, e);
  return stack;
}

void addChar(Buffer *buffer, char c) {
  char *current = ensure(buffer, 1);
  *current++ = c, buffer->offset++;
}

void addChars(Buffer *buffer, char *str, size_t len) {
  char *current = ensure(buffer, len);
  memcpy(current, str, len);
  buffer->offset += len;
}

void handleKey(Buffer *buffer, char *str) {
  char *current;
  size_t escapeCharacters = 0;
  if (str) {
    addChar(buffer, '\"');
    for (unsigned char *p = str; *p; p++) {
      if ((*p > 31) && (*p != '\"') && (*p != '\\')) {
        addChar(buffer, *p);
      } else {
        addChar(buffer, '\\');
        switch (*p) {
          case '\\':
            addChar(buffer, '\\');
            break;
          case '\"':
            addChar(buffer, '\"');
            break;
          case '\b':
            addChar(buffer, 'b');
            break;
          case '\f':
            addChar(buffer, 'f');
            break;
          case '\n':
            addChar(buffer, 'n');
            break;
          case '\r':
            addChar(buffer, 'r');
            break;
          case '\t':
            addChar(buffer, 't');
            break;
          default:
            break;
        }
      }
    }
    addChar(buffer, '\"');
  }
}

void handleString(Buffer *buffer, char *str) {
  if (str) {
    handleKey(buffer, str);
    return;
  }
  addChar(buffer, '\"');
  addChar(buffer, '\"');
}