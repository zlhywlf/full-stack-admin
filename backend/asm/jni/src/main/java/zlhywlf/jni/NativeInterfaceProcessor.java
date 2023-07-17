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
import javax.lang.model.type.TypeMirror;
import javax.tools.JavaFileObject;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Set;
import java.util.UUID;

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
            String interName = inter.getSimpleName().toString();
            String pkgName = inter.getEnclosingElement().toString().replace(".", "/");

            ClassWriter classWriter = new ClassWriter(0);
            MethodVisitor methodVisitor;
            classWriter.visit(V17, ACC_PUBLIC | ACC_SUPER, pkgName + "/" + interName + "Impl2", null, "java/lang/Object", new String[]{pkgName + "/" + interName});
            {
                methodVisitor = classWriter.visitMethod(ACC_PUBLIC, "<init>", "()V", null, null);
                methodVisitor.visitCode();
                methodVisitor.visitVarInsn(ALOAD, 0);
                methodVisitor.visitMethodInsn(INVOKESPECIAL, "java/lang/Object", "<init>", "()V", false);
                methodVisitor.visitInsn(RETURN);
                methodVisitor.visitMaxs(1, 1);
                methodVisitor.visitEnd();
            }

            inter.getEnclosedElements().forEach(
                    interMethod -> {
                        TypeMirror type = interMethod.asType();
                        if (type instanceof Type.MethodType methodType) {
                            List<Type> typeArguments = methodType.getParameterTypes();
                            typeArguments.forEach(t -> {
                                jdk.internal.org.objectweb.asm.Type returnType1 = jdk.internal.org.objectweb.asm.Type.getObjectType(t.toString().replace(".", "/"));
                            });
                            Type returnType = methodType.getReturnType();
                            jdk.internal.org.objectweb.asm.Type returnType1 = jdk.internal.org.objectweb.asm.Type.getObjectType(returnType.toString().replace(".", "/"));
                            System.out.println("1");
                        }
                        String actSignature = interMethod.asType().toString();
                        String expSignature = "(java.lang.String)java.lang.String";
                        String name = interMethod.getSimpleName().toString();
                        if ((interMethod.getKind() == ElementKind.METHOD) && expSignature.equals(actSignature)) {
                            MethodVisitor mv = classWriter.visitMethod(ACC_PUBLIC | ACC_NATIVE, name, "(Ljava/lang/String;)Ljava/lang/String;", null, null);
                            mv.visitEnd();
                            return;
                        }
                        throw new RuntimeException(format(
                                "注解 @NativeInterface 只实现返回类型且存在唯一参数类型均为 java.lang.String 的成员方法，[%s] 不支持的成员定义",
                                interMethod.asType()));
                    }
            );
            classWriter.visitEnd();

            try {
                JavaFileObject source = processingEnv.getFiler().createClassFile(inter.getEnclosingElement().toString() + "." + interName + UUID.randomUUID());
                OutputStream out = source.openOutputStream();
                out.write(classWriter.toByteArray());
                out.flush();
                out.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        return true;
    }

}
