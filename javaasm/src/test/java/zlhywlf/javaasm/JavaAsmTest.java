package zlhywlf.javaasm;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.lang.reflect.InvocationTargetException;
import java.util.function.Consumer;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

import lombok.extern.slf4j.Slf4j;
import zlhywlf.javaasm.classfile.ClassFile;
import zlhywlf.javaasm.classfile.visitor.RawVisitor;
import zlhywlf.javaasm.util.FileUtil;

@Slf4j
@TestInstance(Lifecycle.PER_CLASS)
public class JavaAsmTest {

    @DisplayName("创建类")
    @Test
    void generateClz() throws ClassNotFoundException, InstantiationException, IllegalAccessException,
            IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
        String path = "zlhywlf/javaagent/GenDemo";
        String name = path.replace("/", ".");
        String msg = "hello world!";
        ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_FRAMES);
        cw.visit(Opcodes.V17, Opcodes.ACC_PUBLIC | Opcodes.ACC_SUPER, path, null,
                "java/lang/Object", null);
        {
            MethodVisitor initMethod = cw.visitMethod(Opcodes.ACC_PUBLIC, "<init>", "()V", null, null);
            initMethod.visitCode();
            initMethod.visitVarInsn(Opcodes.ALOAD, 0);
            initMethod.visitMethodInsn(Opcodes.INVOKESPECIAL, "java/lang/Object", "<init>", "()V", false);
            initMethod.visitInsn(Opcodes.RETURN);
            initMethod.visitMaxs(1, 1);
            initMethod.visitEnd();
        }
        {
            MethodVisitor toStringMethod = cw.visitMethod(Opcodes.ACC_PUBLIC, "toString", "()Ljava/lang/String;", null,
                    null);
            toStringMethod.visitCode();
            toStringMethod.visitLdcInsn(msg);
            toStringMethod.visitInsn(Opcodes.ARETURN);
            toStringMethod.visitMaxs(1, 1);
            toStringMethod.visitEnd();
        }
        cw.visitEnd();
        Class<?> clz = new ClassLoader() {
            @Override
            protected Class<?> findClass(String clzName) throws ClassNotFoundException {
                if (name.equals(clzName)) {
                    byte[] byteArray = cw.toByteArray();
                    return defineClass(clzName, byteArray, 0, byteArray.length);
                }
                throw new ClassNotFoundException("not found " + clzName);
            }
        }.loadClass(name);
        assertEquals(clz.getConstructor().newInstance().toString(), msg);
    }

    @DisplayName("输出字节码为十六进制")
    @Test
    void getHex() {
        log.debug("{} bytes\n{}", bytes.length, ClassFile.parse(bytes));
    }

    @DisplayName("输出字节码原始数据")
    @Test
    void getRaw() {
        log.debug("{} bytes\n{}", bytes.length, ClassFile.parse(bytes, new RawVisitor()));
    }

    @BeforeAll
    void beforeAll() {
        String relativePath = Demo.class.getName().replace(".", "/") + ".class";
        String path = FileUtil.getFilePath(relativePath);
        bytes = FileUtil.readFileAsBytes(path);
    }

    byte[] bytes = null;

    class Demo {
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