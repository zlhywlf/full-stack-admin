package zlhywlf.jni;

import org.junit.jupiter.api.*;

import javax.tools.ToolProvider;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class JniTest {

    @DisplayName("编译")
    @Test
    void compilerTest() {
        ToolProvider.getSystemJavaCompiler().run(null, null, null, "-d",
                "./target/test-classes",
                "src/test/java/zlhywlf/jni/model/NativeMethod.java");
    }

}
