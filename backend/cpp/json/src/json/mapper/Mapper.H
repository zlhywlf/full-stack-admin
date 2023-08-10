#pragma once

#include <type_traits>

namespace zlhywlf {
namespace json {
namespace Mapper {
/**
 * @brief SFINAE (Substitution Failure Is Not An Error)
 *
 * @tparam T
 */
template <typename T>
struct hasNecessaryMethod {
  template <typename K>
  static char fn(decltype(&K::serializedKey));
  template <typename K>
  static int fn(...);
  const static bool has = (sizeof(fn<T>(nullptr)) == sizeof(char));
};
}  // namespace Mapper
}  // namespace json
}  // namespace zlhywlf

#include "RapidjsonMapper.H"