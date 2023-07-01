package zlhywlf.javaasm.builder.visitor;

import java.util.Formatter;
import java.util.function.BiConsumer;

import zlhywlf.javaasm.model.ClassFile;
import zlhywlf.javaasm.model.Node;
import zlhywlf.javaasm.util.HexUtil;

/**
 * 按文件结构格式化为十六进制数
 * 
 * @author zlhywlf
 */
public class ClassFileRawVisitorBuilder {

    public BiConsumer<ClassFile, Formatter> build() {
        return magic.andThen(minorVersion).andThen(majorVersion).andThen(constantPoolCount).andThen(constantPool)
                .andThen(accessFlags).andThen(thisClass).andThen(superClass).andThen(interfacesCount)
                .andThen(interfaces).andThen(fieldsCount).andThen(fields).andThen(methodsCount).andThen(methods)
                .andThen(attributesCount).andThen(attributes);
    }

    private BiConsumer<ClassFile, Formatter> attributes = (c, f) -> {
        // TODO
    };

    private BiConsumer<ClassFile, Formatter> attributesCount = (c, f) -> {
        format(f, "u2 methods_count", c.getAttributesCount());
    };

    private BiConsumer<ClassFile, Formatter> methods = (c, f) -> {
        // TODO
    };

    private BiConsumer<ClassFile, Formatter> methodsCount = (c, f) -> {
        format(f, "u2 methods_count", c.getMethodsCount());
    };

    private BiConsumer<ClassFile, Formatter> fields = (c, f) -> {
        // TODO
    };

    private BiConsumer<ClassFile, Formatter> fieldsCount = (c, f) -> {
        format(f, "u2 fields_count", c.getFieldsCount());
    };

    private BiConsumer<ClassFile, Formatter> interfaces = (c, f) -> {
        // TODO
    };

    private BiConsumer<ClassFile, Formatter> interfacesCount = (c, f) -> {
        format(f, "u2 interfaces_count", c.getInterfacesCount());
    };

    private BiConsumer<ClassFile, Formatter> superClass = (c, f) -> {
        format(f, "u2 super_class", c.getSuperClass());
    };

    private BiConsumer<ClassFile, Formatter> thisClass = (c, f) -> {
        format(f, "u2 this_class", c.getThisClass());
    };

    private BiConsumer<ClassFile, Formatter> accessFlags = (c, f) -> {
        format(f, "u2 access_flags", c.getAccessFlags());
    };

    private BiConsumer<ClassFile, Formatter> constantPool = (c, f) -> {
        // TODO
    };

    private BiConsumer<ClassFile, Formatter> constantPoolCount = (c, f) -> {
        format(f, "u2 constant_pool_count", c.getConstantPoolCount());
    };

    private BiConsumer<ClassFile, Formatter> majorVersion = (c, f) -> {
        format(f, "u2 major_version", c.getMajorVersion());
    };

    private BiConsumer<ClassFile, Formatter> magic = (c, f) -> {
        format(f, "u4 magic", c.getMagic());
    };

    private BiConsumer<ClassFile, Formatter> minorVersion = (c, f) -> {
        format(f, "u2 minor_version", c.getMinorVersion());
    };

    private void format(Formatter f, String name, Node node) {
        f.format("%s: %s%n", name, HexUtil.format(node.getBytes()));
    }

}
