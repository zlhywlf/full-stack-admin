package zlhywlf.javaagent;

import java.io.PrintWriter;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

import jdk.internal.org.objectweb.asm.ClassReader;
import jdk.internal.org.objectweb.asm.util.ASMifier;
import jdk.internal.org.objectweb.asm.util.Printer;
import jdk.internal.org.objectweb.asm.util.TraceClassVisitor;

@TestInstance(Lifecycle.PER_CLASS)
public class JavaAgentTest {

    @DisplayName("打印 asm")
    @Test
    void printAsm() throws Exception {
        Printer printer = new ASMifier();
        TraceClassVisitor traceClassVisitor = new TraceClassVisitor(null, printer, new PrintWriter(System.out, true));
        new ClassReader(JavaAgentTest.class.getName()).accept(traceClassVisitor,
                ClassReader.SKIP_FRAMES | ClassReader.SKIP_DEBUG);
    }

}
