package zlhywlf.classfile.builder.parser;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiFunction;

import zlhywlf.classfile.cnst.ClassFileConst;
import zlhywlf.classfile.helper.BytesReader;
import zlhywlf.classfile.model.Constant;
import zlhywlf.classfile.model.cp.*;
import zlhywlf.classfile.util.ByteUtil;

/**
 * 构建常量池解析策略
 * 
 * @author zlhywlf
 */
public class ConstantParserStrategyBuilder {

    public Map<Byte, BiFunction<Integer, BytesReader, Constant>> build() {
        Map<Byte, BiFunction<Integer, BytesReader, Constant>> strategyMap = new HashMap<>(17);
        strategyMap.put(ClassFileConst.CONSTANT_CLASS, constantClass);
        strategyMap.put(ClassFileConst.CONSTANT_DOUBLE, constantDouble);
        strategyMap.put(ClassFileConst.CONSTANT_DYNAMIC, constantDynamic);
        strategyMap.put(ClassFileConst.CONSTANT_FIELDREF, constantFieldref);
        strategyMap.put(ClassFileConst.CONSTANT_FLOAT, constantFloat);
        strategyMap.put(ClassFileConst.CONSTANT_INTEGER, constantInteger);
        strategyMap.put(ClassFileConst.CONSTANT_INTERFACEMETHODREF, constantInterfaceMethodref);
        strategyMap.put(ClassFileConst.CONSTANT_INVOKEDYNAMIC, constantInvokeDynamic);
        strategyMap.put(ClassFileConst.CONSTANT_LONG, constantLong);
        strategyMap.put(ClassFileConst.CONSTANT_METHODHANDLE, constantMethodHandle);
        strategyMap.put(ClassFileConst.CONSTANT_METHODREF, constantMethodref);
        strategyMap.put(ClassFileConst.CONSTANT_METHODTYPE, constantMethodType);
        strategyMap.put(ClassFileConst.CONSTANT_MODULE, constantModule);
        strategyMap.put(ClassFileConst.CONSTANT_NAMEANDTYPE, constantNameAndType);
        strategyMap.put(ClassFileConst.CONSTANT_PACKAGE, constantPackage);
        strategyMap.put(ClassFileConst.CONSTANT_STRING, constantString);
        strategyMap.put(ClassFileConst.CONSTANT_UTF8, constantUtf8);
        return strategyMap;
    }

    private final BiFunction<Integer, BytesReader, Constant> constantUtf8 = (id, reader) -> {
        ConstantUtf8 constant = new ConstantUtf8();
        constant.setId(id);
        constant.setTag(reader.next1());
        constant.setLengthBytes(reader.next2());
        constant.setBytes(reader.next(ByteUtil.toUnsignedInt(constant.getLengthBytes())));
        return constant;
    };

    private final BiFunction<Integer, BytesReader, Constant> constantString = (id, reader) -> {
        ConstantString constant = new ConstantString();
        constant.setId(id);
        constant.setTag(reader.next1());
        constant.setStringIndexBytes(reader.next2());
        return constant;
    };

    private final BiFunction<Integer, BytesReader, Constant> constantPackage = (id, reader) -> {
        ConstantPackage constant = new ConstantPackage();
        constant.setId(id);
        constant.setTag(reader.next1());
        constant.setNameIndexBytes(reader.next2());
        return constant;
    };

    private final BiFunction<Integer, BytesReader, Constant> constantNameAndType = (id, reader) -> {
        ConstantNameAndType constant = new ConstantNameAndType();
        constant.setId(id);
        constant.setTag(reader.next1());
        constant.setNameIndexBytes(reader.next2());
        constant.setDescriptorIndexBytes(reader.next2());
        return constant;
    };

    private final BiFunction<Integer, BytesReader, Constant> constantModule = (id, reader) -> {
        ConstantModule constant = new ConstantModule();
        constant.setId(id);
        constant.setTag(reader.next1());
        constant.setNameIndexBytes(reader.next2());
        return constant;
    };

    private final BiFunction<Integer, BytesReader, Constant> constantMethodType = (id, reader) -> {
        ConstantMethodType constant = new ConstantMethodType();
        constant.setId(id);
        constant.setTag(reader.next1());
        constant.setDescriptorIndexBytes(reader.next2());
        return constant;
    };

    private final BiFunction<Integer, BytesReader, Constant> constantMethodref = (id, reader) -> {
        ConstantMethodref constant = new ConstantMethodref();
        constant.setId(id);
        constant.setTag(reader.next1());
        constant.setClassIndexBytes(reader.next2());
        constant.setNameAndTypeIndexBytes(reader.next2());
        return constant;
    };

    private final BiFunction<Integer, BytesReader, Constant> constantMethodHandle = (id, reader) -> {
        ConstantMethodHandle constant = new ConstantMethodHandle();
        constant.setId(id);
        constant.setTag(reader.next1());
        constant.setReferenceKindBytes(reader.next1());
        constant.setReferenceIndexBytes(reader.next2());
        return constant;
    };

    private final BiFunction<Integer, BytesReader, Constant> constantLong = (id, reader) -> {
        ConstantLong constant = new ConstantLong();
        constant.setId(id);
        constant.setTag(reader.next1());
        constant.setHighBytes(reader.next4());
        constant.setLowBytes(reader.next4());
        return constant;
    };

    private final BiFunction<Integer, BytesReader, Constant> constantInvokeDynamic = (id, reader) -> {
        ConstantInvokeDynamic constant = new ConstantInvokeDynamic();
        constant.setId(id);
        constant.setTag(reader.next1());
        constant.setBootstrapMethodAttrIndexBytes(reader.next2());
        constant.setNameAndTypeIndexBytes(reader.next2());
        return constant;
    };

    private final BiFunction<Integer, BytesReader, Constant> constantInterfaceMethodref = (id, reader) -> {
        ConstantInterfaceMethodref constant = new ConstantInterfaceMethodref();
        constant.setId(id);
        constant.setTag(reader.next1());
        constant.setClassIndexBytes(reader.next2());
        constant.setNameAndTypeIndexBytes(reader.next2());
        return constant;
    };

    private final BiFunction<Integer, BytesReader, Constant> constantInteger = (id, reader) -> {
        ConstantInteger constant = new ConstantInteger();
        constant.setId(id);
        constant.setTag(reader.next1());
        constant.setBytes(reader.next4());
        return constant;
    };

    private final BiFunction<Integer, BytesReader, Constant> constantFloat = (id, reader) -> {
        ConstantFloat constant = new ConstantFloat();
        constant.setId(id);
        constant.setTag(reader.next1());
        constant.setBytes(reader.next4());
        return constant;
    };

    private final BiFunction<Integer, BytesReader, Constant> constantClass = (id, reader) -> {
        ConstantClass constant = new ConstantClass();
        constant.setId(id);
        constant.setTag(reader.next1());
        constant.setNameIndexBytes(reader.next2());
        return constant;
    };

    private final BiFunction<Integer, BytesReader, Constant> constantDouble = (id, reader) -> {
        ConstantDouble constant = new ConstantDouble();
        constant.setId(id);
        constant.setTag(reader.next1());
        constant.setHighBytes(reader.next4());
        constant.setLowBytes(reader.next4());
        return constant;
    };

    private final BiFunction<Integer, BytesReader, Constant> constantDynamic = (id, reader) -> {
        ConstantDynamic constant = new ConstantDynamic();
        constant.setId(id);
        constant.setTag(reader.next1());
        constant.setBootstrapMethodAttrIndexBytes(reader.next2());
        constant.setNameAndTypeIndexBytes(reader.next2());
        return constant;
    };

    private final BiFunction<Integer, BytesReader, Constant> constantFieldref = (id, reader) -> {
        ConstantFieldref constant = new ConstantFieldref();
        constant.setId(id);
        constant.setTag(reader.next1());
        constant.setClassIndexBytes(reader.next2());
        constant.setNameAndTypeIndexBytes(reader.next2());
        return constant;
    };

}
