package zlhywlf.javaasm.builder.parser;

import java.util.function.Function;
import java.util.function.Supplier;

import lombok.RequiredArgsConstructor;
import zlhywlf.javaasm.helper.BytesReader;
import zlhywlf.javaasm.model.ClassFile;
import zlhywlf.javaasm.model.Node;
import zlhywlf.javaasm.util.ByteUtil;

/**
 * 构建 ClassFile
 * 
 * @author zlhywlf
 */
@RequiredArgsConstructor
public class ClassFileBuilder {

    private final BytesReader reader;
    private Function<byte[], Node> func = b -> {
        Node node = new Node();
        node.setBytes(b);
        return node;
    };

    public ClassFile build() {
        ClassFile classFile = new ClassFile();
        Supplier<Node> sup = () -> func.apply(reader.next2());
        classFile.setMagic(func.apply(reader.next4()));
        classFile.setMinorVersion(sup.get());
        classFile.setMajorVersion(sup.get());
        classFile.setConstantPoolCount(sup.get());
        classFile.setConstantPool(
                new ConstantsBuilder(
                        ByteUtil.toUnsignedInt(classFile.getConstantPoolCount().getBytes()),
                        reader).build());
        classFile.setAccessFlags(sup.get());
        classFile.setThisClass(sup.get());
        classFile.setSuperClass(sup.get());
        classFile.setInterfacesCount(sup.get());
        classFile.setInterfaces(
                new InterfacesBuilder(
                        ByteUtil.toUnsignedInt(classFile.getInterfacesCount().getBytes()),
                        reader).build());
        classFile.setFieldsCount(sup.get());
        classFile.setFields(
                new MembersBuilder(
                        ByteUtil.toUnsignedInt(classFile.getFieldsCount().getBytes()),
                        reader).build());
        classFile.setMethodsCount(sup.get());
        classFile.setMethods(
                new MembersBuilder(
                        ByteUtil.toUnsignedInt(classFile.getMethodsCount().getBytes()),
                        reader).build());
        classFile.setAttributesCount(sup.get());
        classFile.setAttributes(
                new AttributesBuilder(
                        ByteUtil.toUnsignedInt(classFile.getAttributesCount().getBytes()),
                        reader).build());
        return classFile;
    }

}
