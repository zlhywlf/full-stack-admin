#pragma once

namespace zlhywlf {
namespace json {
class Model {
  /******************************************************
   *
   * constructors
   *
   ******************************************************/
 public:
  Model() = default;
  Model(const Model &) = delete;

  /******************************************************
   *
   * operators
   *
   ******************************************************/
 public:
  Model &operator=(const Model &) = delete;

  /******************************************************
   *
   * destructor
   *
   ******************************************************/
 public:
  virtual ~Model() = default;
};
}  // namespace json
}  // namespace zlhywlf