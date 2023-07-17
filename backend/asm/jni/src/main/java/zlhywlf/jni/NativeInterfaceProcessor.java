package zlhywlf.jni;

import com.sun.tools.javac.util.List;
import jdk.internal.org.objectweb.asm.*;
import com.sun.tools.javac.code.Type;

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

import static java.lang.String.format;
import static jdk.internal.org.objectweb.asm.Opcodes.*;

@SupportedAnnotationTypes("zlhywlf.jni.NativeInterface")
@SupportedSourceVersion(SourceVersion.RELEASE_17)
public class NativeInterfaceProcessor extends AbstractProcessor {

    @Override
    public boolean process(Set<? extends TypeElement> set, RoundEnvironment roundEnvironment) {
        Set<? extends Element> interSets = roundEnvironment.getElementsAnnotatedWith(NativeInterface.class);
        interSets.forEach(inter -> {
            if (inter.getKind() != ElementKind.INTERFACE) {
                throw new RuntimeException(format("注解 @NativeInterface 只用于接口! [%s] 类型是 [%s]", inter, inter.getKind()));
            }
            // 获取注解信息
            NativeInterface interAnno = inter.getAnnotation(NativeInterface.class);
            // 获取接口信息
            jdk.internal.org.objectweb.asm.Type clzType = transJavacTypeToAsmType(inter.getEnclosingElement() + "." + inter.getSimpleName());
            String suffix = UUID.randomUUID().toString().replace("-", "_");
            // 创建本地方法实现
            createClass(clzType, suffix, classWriter -> inter.getEnclosedElements().forEach(
                    interMethod -> {
                        if (interMethod.asType() instanceof Type.MethodType m) {
                            StringBuilder builder = new StringBuilder("(");
                            List<Type> typeArguments = m.getParameterTypes();
                            typeArguments.forEach(t -> {
                                builder.append(transJavacTypeToAsmType(t).getDescriptor());
                            });
                            builder.append(")");
                            List<Type> thrownTypes = m.getThrownTypes();
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

    private void createClass(jdk.internal.org.objectweb.asm.Type clzType, String suffix, Consumer<ClassWriter> createMethods) {
        final ClassWriter classWriter = new ClassWriter(ClassWriter.COMPUTE_FRAMES);
        classWriter.visit(V17, ACC_PUBLIC | ACC_SUPER, clzType.getInternalName() + suffix, null, "java/lang/Object", new String[]{clzType.getInternalName()});
        MethodVisitor mv = classWriter.visitMethod(ACC_PUBLIC, "<init>", "()V", null, null);
        mv.visitCode();
        mv.visitVarInsn(ALOAD, 0);
        mv.visitMethodInsn(INVOKESPECIAL, "java/lang/Object", "<init>", "()V", false);
        mv.visitInsn(RETURN);
        mv.visitMaxs(1, 1);
        mv.visitEnd();
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

    private jdk.internal.org.objectweb.asm.Type transJavacTypeToAsmType(Type javacType) {
        return transJavacTypeToAsmType(javacType.toString());
    }

    private jdk.internal.org.objectweb.asm.Type transJavacTypeToAsmType(String t) {
        boolean isArr = t.contains("[]");
        if (isArr) {
            t = t.replace("[]", "");
        }
        var asmType = switch (t) {
            case "int" -> jdk.internal.org.objectweb.asm.Type.INT_TYPE;
            case "double" -> jdk.internal.org.objectweb.asm.Type.DOUBLE_TYPE;
            case "float" -> jdk.internal.org.objectweb.asm.Type.FLOAT_TYPE;
            case "long" -> jdk.internal.org.objectweb.asm.Type.LONG_TYPE;
            case "short" -> jdk.internal.org.objectweb.asm.Type.SHORT_TYPE;
            case "char" -> jdk.internal.org.objectweb.asm.Type.CHAR_TYPE;
            case "boolean" -> jdk.internal.org.objectweb.asm.Type.BOOLEAN_TYPE;
            case "byte" -> jdk.internal.org.objectweb.asm.Type.BYTE_TYPE;
            case "void" -> jdk.internal.org.objectweb.asm.Type.VOID_TYPE;
            default -> jdk.internal.org.objectweb.asm.Type.getObjectType(t.replace(".", "/"));
        };
        if (isArr) {
            return jdk.internal.org.objectweb.asm.Type.getType("[" + asmType.getDescriptor());
        }
        return asmType;
    }

}
