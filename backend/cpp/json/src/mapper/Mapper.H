#pragma once

namespace zlhywlf {
namespace json {
/**
 * @brief json 读写接口
 *
 * @tparam T 需要映射或输出的实体对象
 * @tparam K 封装了 json 数据的对象
 */
template <typename T, typename K>
class Mapper {
  /******************************************************
   *
   * constructors
   *
   ******************************************************/
 public:
  Mapper() = default;
  Mapper(const Mapper &) = delete;

  /******************************************************
   *
   * operators
   *
   ******************************************************/
 public:
  Mapper &operator=(const Mapper &) = delete;

  /******************************************************
   *
   * destructor
   *
   ******************************************************/
 public:
  virtual ~Mapper() = default;

  /******************************************************
   *
   * methods
   *
   ******************************************************/
 public:
  /**
   * @brief 将 json 数据映射到实体对象
   *
   * @param obj 实体对象
   * @param adapter 具体的处理对象
   */
  virtual void readValue(T &obj, const K &adapter) = 0;

  /**
   * @brief 将实体对象映射到 json
   *
   * @param obj 实体对象
   * @param adapter 具体的处理对象
   */
  virtual void writeValueAsString(const T &obj, K &adapter) = 0;
};
}  // namespace json
}  // namespace zlhywlf