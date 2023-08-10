#pragma once

#include <string>
#include <vector>

namespace zlhywlf {
namespace string {
namespace Util {
/**
 * @brief 按指定分隔符拆分字符串到容器
 *
 * @param strVector 字符串容器
 * @param str 字符串
 * @param sep 分隔符
 */
void splitStrToVector(std::vector<std::string>& strVector,
                      const std::string& str, char sep) {
  std::string::size_type pos1(0);
  std::string::size_type pos2(str.find(sep));
  auto func = [](std::string& str) {
    if (!str.empty()) {
      str.erase(0, str.find_first_not_of(" "));
      str.erase(str.find_last_not_of(" ") + 1);
    }
  };
  while (std::string::npos != pos2) {
    auto sub(str.substr(pos1, pos2 - pos1));
    func(sub);
    strVector.push_back(sub);
    pos1 = pos2 + 1;
    pos2 = str.find(sep, pos1);
  }
  if (pos1 != str.length()) {
    auto sub(str.substr(pos1));
    func(sub);
    strVector.push_back(sub);
  }
}
}  // namespace Util
}  // namespace string
}  // namespace zlhywlf
