package zlhywlf.classfile.builder.parser;

import java.util.function.Function;
import java.util.function.Supplier;

import lombok.RequiredArgsConstructor;
import zlhywlf.classfile.helper.BytesReader;
import zlhywlf.classfile.model.ClassFile;
import zlhywlf.classfile.model.Node;
import zlhywlf.classfile.util.ByteUtil;

/**
 * 构建 ClassFile
 * 
 * @author zlhywlf
 */
@RequiredArgsConstructor
public class ClassFileBuilder {

    private final BytesReader reader;
    private final Function<byte[], Node> func = b -> {
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
                        reader,
                        classFile.getConstantPool()).build());
        classFile.setMethodsCount(sup.get());
        classFile.setMethods(
                new MembersBuilder(
                        ByteUtil.toUnsignedInt(classFile.getMethodsCount().getBytes()),
                        reader,
                        classFile.getConstantPool()).build());
        classFile.setAttributesCount(sup.get());
        classFile.setAttributes(
                new AttributesBuilder(
                        ByteUtil.toUnsignedInt(classFile.getAttributesCount().getBytes()),
                        reader,
                        classFile.getConstantPool()).build());
        return classFile;
    }

}
