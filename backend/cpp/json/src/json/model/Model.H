#pragma once

#include <string>

namespace zlhywlf {
namespace json {
template <typename T>
class Model {
 public:
  std::string name;
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

  /******************************************************
   *
   * destructor
   *
   ******************************************************/
 public:
  virtual ~Model() = default;

  /******************************************************
   *
   * methods
   *
   ******************************************************/
 public:
  T read(const std::string &key) const;
  T &write(const std::string &key);
};

template <typename T>
T Model<T>::read(const std::string &key) const {
  return static_cast<const T *>(this)->read(key);
}

template <typename T>
T &Model<T>::write(const std::string &key) {
  return static_cast<T *>(this)->write(key);
}
}  // namespace json
}  // namespace zlhywlf