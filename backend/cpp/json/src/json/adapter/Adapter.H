#pragma once

#include <string>

namespace zlhywlf {
namespace json {
/**
 * @brief CRTP（Curiously Recurring Template Prattern）
 *
 * @tparam T 子类
 */
template <typename T>
class Adapter {
  /******************************************************
   *
   * constructors
   *
   ******************************************************/
 public:
  Adapter(const std::string &jsonStr);
  Adapter(const Adapter &) = delete;

  /******************************************************
   *
   * operators
   *
   ******************************************************/
 public:
  Adapter &operator=(const Adapter &) = delete;

  /******************************************************
   *
   * destructor
   *
   ******************************************************/
 public:
  ~Adapter() = default;

  /******************************************************
   *
   * methods
   *
   ******************************************************/
 public:
  /**
   * @brief 将 json 数据映射到实体对象
   *
   * @tparam B 任意类型
   * @param obj 实体对象
   */
  template <typename B>
  void readValue(B &obj);

  /**
   * @brief 将实体对象映射到 json
   *
   * @tparam B 任意类型
   * @param obj 实体对象
   */
  template <typename B>
  void writeValueAsString(const B &obj);
};

template <typename T>
Adapter<T>::Adapter(const std::string &jsonStr) {}

template <typename T>
template <typename B>
void Adapter<T>::readValue(B &obj) {
  static_cast<T *>(this)->readValue(obj);
}

template <typename T>
template <typename B>
void Adapter<T>::writeValueAsString(const B &obj) {
  static_cast<T *>(this)->writeValueAsString(obj);
}
}  // namespace json
}  // namespace zlhywlf