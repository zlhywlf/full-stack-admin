package zlhywlf.javaagent;

import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.lang.instrument.Instrumentation;
import java.security.ProtectionDomain;
import java.util.Arrays;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Main {
    public static void main(String[] args) {
        log.debug("main args : {}", Arrays.toString(args));
    }

    public static void premain(String args, Instrumentation inst) {
        log.debug("premain args : {}", args);
        String name = "zlhywlf/javaagent/AppTest";
        inst.addTransformer(new ClassFileTransformer() {
            @Override
            public byte[] transform(ClassLoader loader, String className, Class<?> classBeingRedefined,
                    ProtectionDomain protectionDomain, byte[] classfileBuffer)
                    throws IllegalClassFormatException {
                if (name.equals(className)) {
                    log.debug("premain load Class : {}", className);
                }
                return classfileBuffer;
            }
        }, true);
    }

    public static void agentmain(String args, Instrumentation inst) {
        log.debug("agentmain args : {}", args);
    }
}
