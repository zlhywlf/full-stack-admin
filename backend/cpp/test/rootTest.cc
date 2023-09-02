#include <iostream>

#include "logger/LoggerFactory.H"

using std::cout;
using std::endl;
using zlhywlf::logger::LoggerFactory::createLogger;

int main(int argc, char const *argv[]) {
  auto &log = createLogger();
  log.trace("msg");
  log.debug("msg");
  log.info("msg");
  log.warn("msg");
  log.error("msg");
  return 0;
}