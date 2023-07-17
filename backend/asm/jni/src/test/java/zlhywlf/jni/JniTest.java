package zlhywlf.jni;

import jdk.internal.org.objectweb.asm.ClassReader;
import jdk.internal.org.objectweb.asm.util.ASMifier;
import jdk.internal.org.objectweb.asm.util.Printer;
import jdk.internal.org.objectweb.asm.util.TraceClassVisitor;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import zlhywlf.jni.model.NativeMethod;
import zlhywlf.jni.model.NativeMethodTmp;

import javax.tools.ToolProvider;
import java.io.IOException;
import java.io.PrintWriter;

@Slf4j
@Configuration
@ComponentScan("zlhywlf.jni")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class JniTest {

    AnnotationConfigApplicationContext context;
    String param = "{\"key\":\"any\"}";

    void compiler(String name) {
        ToolProvider.getSystemJavaCompiler().run(null, null, null, "-d",
                "./target/test-classes",
                "src/test/java/zlhywlf/jni/model/" + name + ".java");
    }

    @BeforeAll
    void beforeAllTest() throws IOException {
//        context = new AnnotationConfigApplicationContext(JniTest.class);
    }

    @AfterAll
    void afterAllTest() {
//        context.close();
    }

    @DisplayName("编译")
    @Test
    void compilerTest() {
        compiler("NativeMethod");
    }

    @DisplayName("spring, 重复执行多次, 默认全局对象只会创建与删除一次")
    @RepeatedTest(3)
    void runForSpringTest() throws IOException {
        NativeMethod bean = context.getBean(NativeMethod.class);
        bean.demo(param, 1, (char) 2, (byte) 3, (short) 4, 5L, 6F, 7D, true, new String[0]);
    }

    public static void main(String[] args) throws IOException {
        Printer printer = new ASMifier();
        TraceClassVisitor traceClassVisitor = new TraceClassVisitor(null, printer, new PrintWriter(System.out, true));
        new ClassReader(NativeMethodTmp.class.getName()).accept(traceClassVisitor,
                ClassReader.SKIP_FRAMES | ClassReader.SKIP_DEBUG);
    }

}
