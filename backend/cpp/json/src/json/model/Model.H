#pragma once

#include <string>

#include "logger/LoggerFactory.H"

namespace zlhywlf {
namespace json {
template <typename T>
class Model {
 public:
  mutable std::string name;
  const logger::Logger &log{logger::LoggerFactory::createLogger()};

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
  const T &read(const std::string &key) const;
  T &write(const std::string &key);
};

template <typename T>
const T &Model<T>::read(const std::string &key) const {
  name = key;
  return static_cast<const T &>(*this);
}

template <typename T>
T &Model<T>::write(const std::string &key) {
  name = key;
  return static_cast<T &>(*this);
}
}  // namespace json
}  // namespace zlhywlf