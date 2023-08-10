#pragma once

#include <string>

namespace zlhywlf {
namespace json {
template <typename T>
class Model {
  /******************************************************
   *
   * constructors
   *
   ******************************************************/
 public:
  Model() = default;
  Model(const Model &) = default;

  /******************************************************
   *
   * operators
   *
   ******************************************************/
 public:
  Model &operator=(const Model &) = default;
  T operator[](const std::string &key) const;

  /******************************************************
   *
   * destructor
   *
   ******************************************************/
 public:
  virtual ~Model() = default;
};

template <typename T>
T Model<T>::operator[](const std::string &key) const {
  return static_cast<const T *>(this)->operator[](key);
}
}  // namespace json
}  // namespace zlhywlf