package zlhywlf.jni;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import zlhywlf.jni.model.NativeMethod;

import javax.tools.ToolProvider;
import java.io.IOException;

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
        context = new AnnotationConfigApplicationContext(JniTest.class);
    }

    @AfterAll
    void afterAllTest() {
        context.close();
    }

    @DisplayName("编译")
    @Test
    void compilerTest() {
        compiler("NativeMethod");
    }

    @DisplayName("spring, 重复执行多次, 默认全局对象只会创建与删除一次")
    @RepeatedTest(3)
    void runForSpringTest() {
        NativeMethod bean = context.getBean(NativeMethod.class);
        String result = bean.demo(param);
        log.info(result);
    }

}
