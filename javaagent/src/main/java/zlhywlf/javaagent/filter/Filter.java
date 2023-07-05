package zlhywlf.javaagent.filter;

import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.security.ProtectionDomain;
import java.util.function.Consumer;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.MethodNode;

public abstract class Filter extends ClassNode implements ClassFileTransformer, Consumer<MethodNode> {

    private String targetName;

    public Filter(String targetName) {
        super(Opcodes.ASM9);
        this.targetName = targetName;
    }

    @Override
    public byte[] transform(ClassLoader loader, String className, Class<?> classBeingRedefined,
            ProtectionDomain protectionDomain, byte[] classfileBuffer)
            throws IllegalClassFormatException {
        if (targetName.equals(className)) {
            System.out.println("premain load Class :  " + className);
            ClassReader cr = new ClassReader(classfileBuffer);
            ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_FRAMES);
            cv = cw;
            cr.accept(this, ClassReader.SKIP_DEBUG | ClassReader.SKIP_FRAMES);
            return cw.toByteArray();
        }
        return classfileBuffer;
    }

    @Override
    public void visitEnd() {
        methods.forEach(this::accept);
        super.visitEnd();
        accept(cv);
    }

}
