package zlhywlf.javaasm;

import java.io.Serializable;
import java.util.function.Consumer;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

import lombok.extern.slf4j.Slf4j;
import zlhywlf.classfile.util.ClassFileUtil;
import zlhywlf.classfile.util.FileUtil;

@Slf4j
@TestInstance(Lifecycle.PER_CLASS)
public class JavaAsmTest {

    @DisplayName("输出字节码为十六进制")
    @Test
    void getHex() {
        log.debug("{} bytes\n{}", bytes.length, ClassFileUtil.parse(bytes));
    }

    @DisplayName("格式化字节码十六进制")
    @Test
    void parseRaw() {
        log.debug("{} bytes\n{}", bytes.length, ClassFileUtil.parseRaw(bytes));
    }

    @DisplayName("字节码一般解析")
    @Test
    void parseSimple() {
        log.debug("{} bytes\n{}", bytes.length, ClassFileUtil.parseSimple(bytes));
    }

    @BeforeAll
    void beforeAll() {
        String relativePath = Demo.class.getName().replace(".", "/") + ".class";
        String path = FileUtil.getFilePath(relativePath);
        bytes = FileUtil.readFileAsBytes(path);
    }

    byte[] bytes = null;

    class Demo implements Serializable, Cloneable {
        public static final int a = Integer.MAX_VALUE;
        public static final double b = Double.MAX_VALUE;
        public static final float c = Float.MAX_VALUE;
        public static final long d = Long.MAX_VALUE;
        public static final String e = "hello world!";
        private int f = 2;
        private A g;
        {
            g.run(f);
        }

        Consumer<Integer> h = System.out::println;

    }

}

interface A {
    void run(int i);
}