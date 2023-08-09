#include <iostream>

#include "logger/LoggerFactory.H"

using std::cout;
using std::endl;

int main(int argc, char const *argv[]) {
  zlhywlf::logger::LoggerFactory f;
  auto *log = f.createLogger();
  log->trace("msg");
  log->debug("msg");
  log->info("msg");
  log->warn("msg");
  log->error("msg");
  delete log;
  return 0;
}