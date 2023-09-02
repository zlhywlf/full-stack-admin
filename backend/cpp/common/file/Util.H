#pragma once

#include <fstream>
#include <sstream>
#include <string>

namespace zlhywlf {
namespace file {
namespace Util {
/**
 * @brief 读取文件为字符串
 *
 * @param path 文件路径
 * @param str 结果字符串
 * @return true 成功
 * @return false 失败
 */
inline bool readFileAsString(const std::string &path, std::string &str) {
  std::ifstream file(path);
  bool isOk = false;
  if (file.is_open()) {
    std::ostringstream buf;
    char ch;
    while (buf && file.get(ch)) {
      buf.put(ch);
    }
    str = buf.str();
    isOk = true;
  }
  file.close();
  return isOk;
}
}  // namespace Util
}  // namespace file
}  // namespace zlhywlf