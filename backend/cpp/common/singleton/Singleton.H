#pragma once

#include <mutex>

namespace zlhywlf {
namespace singleton {
template <typename T>
class Singleton final {
 private:
  static std::unique_ptr<T> instance_;

  /******************************************************
   *
   * constructors
   *
   ******************************************************/
 public:
  Singleton() = delete;
  Singleton(const Singleton &) = delete;

  /******************************************************
   *
   * operators
   *
   ******************************************************/
 public:
  Singleton &operator=(const Singleton &) = delete;

  /******************************************************
   *
   * destructor
   *
   ******************************************************/
 public:
  ~Singleton() = default;

 public:
  template <typename... Args>
  static T &getInstance(Args &&...args);
};

template <typename T>
std::unique_ptr<T> Singleton<T>::instance_ = nullptr;

template <typename T>
template <typename... Args>
T &Singleton<T>::getInstance(Args &&...args) {
  if (!instance_) {
    static std::once_flag flag;
    std::call_once(
        flag, [&]() { instance_.reset(new T(std::forward<Args>(args)...)); });
  }
  return *instance_;
}
}  // namespace singleton
}  // namespace zlhywlf