#pragma once

#include <stdlib.h>

/**
 * @brief 内存管理
 *
 */
struct {
  void *(*allocate)(size_t);
  void (*deallocate)(void *);
  void *(*reallocate)(void *, size_t);
} mem = {malloc, free, realloc};

/**
 * @brief json 对象类型
 *
 */
typedef enum {
  J_NULL = 1 << 0,
  J_FALSE = 1 << 1,
  J_TRUE = 1 << 2,
  J_NUMBER = 1 << 3,
  J_STRING = 1 << 4,
  J_ARRAY = 1 << 5,
  J_OBJECT = 1 << 6
} Type;

/**
 * @brief json 值
 *
 */
typedef union {
  struct Json *child;
  char *str;
  double num;
} Value;

/**
 * @brief json 对象链表
 *
 */
typedef struct Json {
  struct Json *next;
  struct Json *prev;
  Type t;
  char *k;
  Value *v;
} Json;

/**
 * @brief 数据栈
 *
 */
typedef struct Stack {
  Json *json;
  struct Stack *next;
} Stack;

/**
 * @brief json 字串访问
 *
 */
typedef struct {
  char *str;
  size_t len;
  size_t offset;
} Buffer;

/**
 * @brief 确保内存足够容纳输出的字符，否则进行扩容并返回 buffer->str
 * 当前位置指针
 *
 * @param buffer
 * @param needed
 * @return char* 不需要释放指针
 */
char *ensure(Buffer *buffer, size_t needed);

/**
 * @brief 分配内存
 *
 * @param size 内存大小
 * @return void* 需要释放指针
 */
void *newMem(size_t size);

/**
 * @brief 创建 Json 对象
 *
 * @return Json* 需要释放指针
 */
Json *newJson();

/**
 * @brief 创建值
 *
 * @return Value*
 */
Value *newValue();

/**
 * @brief 创建 json 节点
 *
 * @param t 节点类型
 * @return Json* 需要释放指针
 */
Json *newItem(Type t);

/**
 * @brief J_NULL
 *
 * @return Json* 需要释放指针
 */
Json *newNullItem();

/**
 * @brief J_FALSE
 *
 * @return Json* 需要释放指针
 */
Json *newFalseItem();

/**
 * @brief J_TRUE
 *
 * @return Json* 需要释放指针
 */
Json *newTrueItem();

/**
 * @brief J_NUMBER
 *
 * @return Json* 需要释放指针
 */
Json *newNumberItem();

/**
 * @brief J_STRING
 *
 * @return Json* 需要释放指针
 */
Json *newStringItem();

/**
 * @brief J_ARRAY
 *
 * @return Json* 需要释放指针
 */
Json *newArrayItem();

/**
 * @brief J_OBJECT
 *
 * @return Json* 需要释放指针
 */
Json *newObjectItem();

/**
 * @brief 将 json 对象输出为字符串
 *
 * @param json
 * @return char* 需要释放指针
 */
char *toString(Json *json);

/**
 * @brief 创建 Stack 对象
 *
 * @return Stack* 出栈时释放指针
 */
Stack *newStack();

/**
 * @brief 入栈
 *
 * @param stack
 * @param json
 * @return Stack* 出栈时释放指针
 */
Stack *push(Stack *stack, Json *json);

/**
 * @brief 出栈
 *
 * @param stack
 * @param buffer
 * @return Stack* 出栈时释放指针
 */
Stack *pop(Stack *stack);

/**
 * @brief 处理 J_OBJECT 或 J_ARRAY
 *
 * @param stack
 * @param buffer
 * @param s
 * @param e
 * @return Stack*
 */
Stack *handleObjectOrArray(Stack *stack, Buffer *buffer, char s, char e);

/**
 * @brief 添加字符
 *
 * @param buffer
 * @param c
 */
void addChar(Buffer *buffer, char c);

/**
 * @brief 添加字符串
 *
 * @param buffer
 * @param str
 * @param len
 */
void addChars(Buffer *buffer, char *str, size_t len);

/**
 * @brief 处理键
 *
 * @param buffer
 * @param str
 * @return Stack*
 */
void handleKey(Buffer *buffer, char *str);

/**
 * @brief 处理 J_STRING
 *
 * @param buffer
 * @param str
 */
void handleString(Buffer *buffer, Value *v);