package zlhywlf.javaasm.builder.visitor;

import java.util.Formatter;
import java.util.function.BiConsumer;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import zlhywlf.javaasm.model.ClassFile;
import zlhywlf.javaasm.visitor.ClassFileVisitor;

/**
 * ClassFile 格式化构建
 * 
 * @author zlhywlf
 */
@RequiredArgsConstructor
public class ClassFileVisitorConsumerBuilder {

    @NonNull
    private ClassFileVisitor visitor;

    public BiConsumer<ClassFile, Formatter> build() {
        return magic.andThen(minorVersion).andThen(majorVersion).andThen(constantPoolCount).andThen(constantPool)
                .andThen(accessFlags).andThen(thisClass).andThen(superClass).andThen(interfacesCount)
                .andThen(interfaces).andThen(fieldsCount).andThen(fields).andThen(methodsCount).andThen(methods)
                .andThen(attributesCount).andThen(attributes);
    }

    private BiConsumer<ClassFile, Formatter> attributes = (c, f) -> {
        visitor.visitAttributes(f, c.getAttributes());
    };

    private BiConsumer<ClassFile, Formatter> attributesCount = (c, f) -> {
        visitor.visitAttributesCount(f, c.getAttributesCount());
    };

    private BiConsumer<ClassFile, Formatter> methods = (c, f) -> {
        visitor.visitMethods(f, c.getMethods());
    };

    private BiConsumer<ClassFile, Formatter> methodsCount = (c, f) -> {
        visitor.visitMethodsCount(f, c.getMethodsCount());
    };

    private BiConsumer<ClassFile, Formatter> fields = (c, f) -> {
        visitor.visitFields(f, c.getFields());
    };

    private BiConsumer<ClassFile, Formatter> fieldsCount = (c, f) -> {
        visitor.visitFieldsCount(f, c.getFieldsCount());
    };

    private BiConsumer<ClassFile, Formatter> interfaces = (c, f) -> {
        visitor.visitInterfaces(f, c.getInterfaces());
    };

    private BiConsumer<ClassFile, Formatter> interfacesCount = (c, f) -> {
        visitor.visitInterfacesCount(f, c.getInterfacesCount());
    };

    private BiConsumer<ClassFile, Formatter> superClass = (c, f) -> {
        visitor.visitSuperClass(f, c.getSuperClass());
    };

    private BiConsumer<ClassFile, Formatter> thisClass = (c, f) -> {
        visitor.visitThisClass(f, c.getThisClass());
    };

    private BiConsumer<ClassFile, Formatter> accessFlags = (c, f) -> {
        visitor.visitAccessFlags(f, c.getAccessFlags());
    };

    private BiConsumer<ClassFile, Formatter> constantPool = (c, f) -> {
        visitor.visitConstantPool(f, c.getConstantPool());
    };

    private BiConsumer<ClassFile, Formatter> constantPoolCount = (c, f) -> {
        visitor.visitConstantPoolCount(f, c.getConstantPoolCount());
    };

    private BiConsumer<ClassFile, Formatter> majorVersion = (c, f) -> {
        visitor.visitMajorVersion(f, c.getMajorVersion());
    };

    private BiConsumer<ClassFile, Formatter> magic = (c, f) -> {
        visitor.visitMagic(f, c.getMagic());
    };

    private BiConsumer<ClassFile, Formatter> minorVersion = (c, f) -> {
        visitor.visitMinorVersion(f, c.getMinorVersion());
    };

}
