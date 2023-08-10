#include <iostream>

#include "singleton/Singleton.H"

using std::cout;
using std::endl;
using zlhywlf::singleton::Singleton;

class SingletonTest {
  const char *name_;

 public:
  SingletonTest() : name_("foo") { cout << "create" << endl; }
  SingletonTest(const char *name) : name_(name) { cout << "create" << endl; }
  void run() { cout << this << ": " << name_ << endl; }
  ~SingletonTest() { cout << "delete" << endl; }
};

int main(int argc, char const *argv[]) {
  auto &s1 = Singleton<SingletonTest>::getInstance("bar");
  auto &s2 = Singleton<SingletonTest>::getInstance();
  s1.run();
  s2.run();
  assert(&s1 == &s2);
  return 0;
}