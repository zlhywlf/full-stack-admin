#pragma once

#include <string>

namespace zlhywlf {
namespace json {
/**
 * @brief CRTP（Curiously Recurring Template Prattern）
 *
 * @tparam T 子类
 * @tparam K 含 json 数据的对象
 */
template <typename T, typename K>
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

 public:
  /******************************************************
   *
   * getters and setters
   *
   ******************************************************/
  K model() const;
};

template <typename T, typename K>
Adapter<T, K>::Adapter(const std::string &jsonStr) {}

template <typename T, typename K>
K Adapter<T, K>::model() const {
  return static_cast<const T *>(this)->model();
}
}  // namespace json
}  // namespace zlhywlf