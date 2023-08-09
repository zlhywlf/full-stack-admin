#include <iostream>

#include "logger/DefaultLoggerFactory.H"

using std::cout;
using std::endl;

int main(int argc, char const *argv[]) {
  zlhywlf::logger::DefaultLoggerFactory f;
  auto *log = f.createLogger();
  log->trace("msg");
  log->debug("msg");
  log->info("msg");
  log->warn("msg");
  log->error("msg");
  delete log;
  return 0;
}