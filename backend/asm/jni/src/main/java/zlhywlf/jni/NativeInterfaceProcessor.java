package zlhywlf.jni;

import com.sun.tools.javac.util.List;
import jdk.internal.org.objectweb.asm.*;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.TypeElement;
import javax.tools.JavaFileObject;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Set;
import java.util.UUID;
import java.util.function.Consumer;

import static jdk.internal.org.objectweb.asm.Opcodes.*;

@SupportedAnnotationTypes("zlhywlf.jni.NativeInterface")
@SupportedSourceVersion(SourceVersion.RELEASE_17)
public class NativeInterfaceProcessor extends AbstractProcessor {

    @Override
    public boolean process(Set<? extends TypeElement> set, RoundEnvironment roundEnvironment) {
        Set<? extends Element> interSets = roundEnvironment.getElementsAnnotatedWith(NativeInterface.class);
        interSets.forEach(inter -> {
            if (inter.getKind() != ElementKind.INTERFACE) {
                throw new RuntimeException(String.format("注解 @NativeInterface 只用于接口! [%s] 类型是 [%s]", inter, inter.getKind()));
            }
            NativeInterface interAnno = inter.getAnnotation(NativeInterface.class);
            String[] anno = interAnno.anno();
            String libName = interAnno.name();
            if (libName == null || libName.isEmpty()) {
                libName = inter.getSimpleName().toString();
            }
            Type clzType = transJavacTypeToAsmType(inter.getEnclosingElement() + "." + inter.getSimpleName());
            createClass(clzType, libName, anno, classWriter -> inter.getEnclosedElements().forEach(
                    interMethod -> {
                        if (interMethod.asType() instanceof com.sun.tools.javac.code.Type.MethodType m) {
                            StringBuilder builder = new StringBuilder("(");
                            List<com.sun.tools.javac.code.Type> typeArguments = m.getParameterTypes();
                            typeArguments.forEach(t -> builder.append(transJavacTypeToAsmType(t).getDescriptor()));
                            builder.append(")");
                            List<com.sun.tools.javac.code.Type> thrownTypes = m.getThrownTypes();
                            String[] exceptions = new String[thrownTypes.size()];
                            for (int i = 0; i < exceptions.length; i++) {
                                exceptions[i] = transJavacTypeToAsmType(thrownTypes.get(i)).getInternalName();
                            }
                            builder.append(transJavacTypeToAsmType(m.getReturnType()));
                            classWriter.visitMethod(ACC_PUBLIC | ACC_NATIVE, interMethod.getSimpleName().toString(), builder.toString(), null, exceptions).visitEnd();
                        }
                    }
            ));
        });
        return true;
    }

    private void createClass(Type clzType, String libName, String[] anno, Consumer<ClassWriter> createMethods) {
        String suffix = UUID.randomUUID().toString().replace("-", "_");
        final ClassWriter classWriter = new ClassWriter(ClassWriter.COMPUTE_FRAMES);
        classWriter.visit(V17, ACC_PUBLIC | ACC_SUPER, clzType.getInternalName() + suffix, null, "java/lang/Object", new String[]{clzType.getInternalName()});
        MethodVisitor mv = classWriter.visitMethod(ACC_PUBLIC, "<init>", "()V", null, null);
        mv.visitCode();
        mv.visitVarInsn(ALOAD, 0);
        mv.visitMethodInsn(INVOKESPECIAL, "java/lang/Object", "<init>", "()V", false);
        mv.visitInsn(RETURN);
        mv.visitMaxs(1, 1);
        mv.visitEnd();
        mv = classWriter.visitMethod(ACC_STATIC, "<clinit>", "()V", null, null);
        mv.visitCode();
        mv.visitLdcInsn(libName);
        mv.visitMethodInsn(INVOKESTATIC, "java/lang/System", "loadLibrary", "(Ljava/lang/String;)V", false);
        mv.visitInsn(RETURN);
        mv.visitMaxs(1, 0);
        mv.visitEnd();
        AnnotationVisitor av;
        for (String a : anno) {
            av = classWriter.visitAnnotation(transJavacTypeToAsmType(a).getDescriptor(), true);
            av.visitEnd();
        }
        createMethods.accept(classWriter);
        classWriter.visitEnd();
        try {
            JavaFileObject source = processingEnv.getFiler().createClassFile(clzType.getClassName() + suffix);
            OutputStream out = source.openOutputStream();
            out.write(classWriter.toByteArray());
            out.flush();
            out.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private Type transJavacTypeToAsmType(com.sun.tools.javac.code.Type javacType) {
        return transJavacTypeToAsmType(javacType.toString());
    }

    private Type transJavacTypeToAsmType(String t) {
        boolean isArr = t.contains("[]");
        if (isArr) {
            t = t.replace("[]", "");
        }
        var asmType = switch (t) {
            case "int" -> Type.INT_TYPE;
            case "double" -> Type.DOUBLE_TYPE;
            case "float" -> Type.FLOAT_TYPE;
            case "long" -> Type.LONG_TYPE;
            case "short" -> Type.SHORT_TYPE;
            case "char" -> Type.CHAR_TYPE;
            case "boolean" -> Type.BOOLEAN_TYPE;
            case "byte" -> Type.BYTE_TYPE;
            case "void" -> Type.VOID_TYPE;
            default -> Type.getObjectType(t.replace(".", "/"));
        };
        if (isArr) {
            return Type.getType("[" + asmType.getDescriptor());
        }
        return asmType;
    }

}
