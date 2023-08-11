package zlhywlf.jni.demo;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Main {
    public static void main(String[] args) {
        log.info("jni-demo");
        if (args.length > 0) {
            String libPath = args[0];
            System.load(libPath);
            Demo demo = new Demo();
            log.info("from c++: {}", demo.run("""
                    {"info":"from java}
                    """));
        }
    }
}

@Slf4j
class Demo {
    public native String run(String p);
}