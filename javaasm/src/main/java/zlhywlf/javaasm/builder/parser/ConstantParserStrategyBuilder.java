package zlhywlf.javaasm.builder.parser;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiFunction;

import zlhywlf.javaasm.cnst.ClassFileConst;
import zlhywlf.javaasm.helper.BytesReader;
import zlhywlf.javaasm.model.Constant;
import zlhywlf.javaasm.model.cp.*;
import zlhywlf.javaasm.util.ByteUtil;

/**
 * 构建常量池解析策略
 * 
 * @author zlhywlf
 */
public class ConstantParserStrategyBuilder {

    public Map<Byte, BiFunction<Integer, BytesReader, Constant>> build() {
        Map<Byte, BiFunction<Integer, BytesReader, Constant>> strategyMap = new HashMap<>(17);
        strategyMap.put(ClassFileConst.CONSTANT_CLASS, constantClassParser);
        strategyMap.put(ClassFileConst.CONSTANT_DOUBLE, constantDoubleParser);
        strategyMap.put(ClassFileConst.CONSTANT_DYNAMIC, constantDynamicParser);
        strategyMap.put(ClassFileConst.CONSTANT_FIELDREF, constantFieldrefParser);
        strategyMap.put(ClassFileConst.CONSTANT_FLOAT, constantFloatParser);
        strategyMap.put(ClassFileConst.CONSTANT_INTEGER, constantIntegerParser);
        strategyMap.put(ClassFileConst.CONSTANT_INTERFACEMETHODREF, constantInterfaceMethodrefParser);
        strategyMap.put(ClassFileConst.CONSTANT_INVOKEDYNAMIC, constantInvokeDynamicParser);
        strategyMap.put(ClassFileConst.CONSTANT_LONG, constantLongParser);
        strategyMap.put(ClassFileConst.CONSTANT_METHODHANDLE, constantMethodHandleParser);
        strategyMap.put(ClassFileConst.CONSTANT_METHODREF, constantMethodrefParser);
        strategyMap.put(ClassFileConst.CONSTANT_METHODTYPE, constantMethodTypeParser);
        strategyMap.put(ClassFileConst.CONSTANT_MODULE, constantModuleParser);
        strategyMap.put(ClassFileConst.CONSTANT_NAMEANDTYPE, constantNameAndTypeParser);
        strategyMap.put(ClassFileConst.CONSTANT_PACKAGE, constantPackageParser);
        strategyMap.put(ClassFileConst.CONSTANT_STRING, constantStringParser);
        strategyMap.put(ClassFileConst.CONSTANT_UTF8, constantUtf8Parser);
        return strategyMap;
    }

    private final BiFunction<Integer, BytesReader, Constant> constantUtf8Parser = (id, reader) -> {
        ConstantUtf8 constant = new ConstantUtf8();
        constant.setId(id);
        constant.setTag(reader.next1());
        constant.setLengthBytes(reader.next2());
        constant.setBytes(reader.next(ByteUtil.toUnsignedInt(constant.getLengthBytes())));
        return constant;
    };

    private final BiFunction<Integer, BytesReader, Constant> constantStringParser = (id, reader) -> {
        ConstantString constant = new ConstantString();
        constant.setId(id);
        constant.setTag(reader.next1());
        constant.setStringIndexBytes(reader.next2());
        return constant;
    };

    private final BiFunction<Integer, BytesReader, Constant> constantPackageParser = (id, reader) -> {
        ConstantPackage constant = new ConstantPackage();
        constant.setId(id);
        constant.setTag(reader.next1());
        constant.setNameIndexBytes(reader.next2());
        return constant;
    };

    private final BiFunction<Integer, BytesReader, Constant> constantNameAndTypeParser = (id, reader) -> {
        ConstantNameAndType constant = new ConstantNameAndType();
        constant.setId(id);
        constant.setTag(reader.next1());
        constant.setNameIndexBytes(reader.next2());
        constant.setDescriptorIndexBytes(reader.next2());
        return constant;
    };

    private final BiFunction<Integer, BytesReader, Constant> constantModuleParser = (id, reader) -> {
        ConstantModule constant = new ConstantModule();
        constant.setId(id);
        constant.setTag(reader.next1());
        constant.setNameIndexBytes(reader.next2());
        return constant;
    };

    private final BiFunction<Integer, BytesReader, Constant> constantMethodTypeParser = (id, reader) -> {
        ConstantMethodType constant = new ConstantMethodType();
        constant.setId(id);
        constant.setTag(reader.next1());
        constant.setDescriptorIndexBytes(reader.next2());
        return constant;
    };

    private final BiFunction<Integer, BytesReader, Constant> constantMethodrefParser = (id, reader) -> {
        ConstantMethodref constant = new ConstantMethodref();
        constant.setId(id);
        constant.setTag(reader.next1());
        constant.setClassIndexBytes(reader.next2());
        constant.setNameAndTypeIndexBytes(reader.next2());
        return constant;
    };

    private final BiFunction<Integer, BytesReader, Constant> constantMethodHandleParser = (id, reader) -> {
        ConstantMethodHandle constant = new ConstantMethodHandle();
        constant.setId(id);
        constant.setTag(reader.next1());
        constant.setReferenceKindBytes(reader.next1());
        constant.setReferenceIndexBytes(reader.next2());
        return constant;
    };

    private final BiFunction<Integer, BytesReader, Constant> constantLongParser = (id, reader) -> {
        ConstantLong constant = new ConstantLong();
        constant.setId(id);
        constant.setTag(reader.next1());
        constant.setHighBytes(reader.next4());
        constant.setLowBytes(reader.next4());
        return constant;
    };

    private final BiFunction<Integer, BytesReader, Constant> constantInvokeDynamicParser = (id, reader) -> {
        ConstantInvokeDynamic constant = new ConstantInvokeDynamic();
        constant.setId(id);
        constant.setTag(reader.next1());
        constant.setBootstrapMethodAttrIndexBytes(reader.next2());
        constant.setNameAndTypeIndexBytes(reader.next2());
        return constant;
    };

    private final BiFunction<Integer, BytesReader, Constant> constantInterfaceMethodrefParser = (id, reader) -> {
        ConstantInterfaceMethodref constant = new ConstantInterfaceMethodref();
        constant.setId(id);
        constant.setTag(reader.next1());
        constant.setClassIndexBytes(reader.next2());
        constant.setNameAndTypeIndexBytes(reader.next2());
        return constant;
    };

    private final BiFunction<Integer, BytesReader, Constant> constantIntegerParser = (id, reader) -> {
        ConstantInteger constant = new ConstantInteger();
        constant.setId(id);
        constant.setTag(reader.next1());
        constant.setBytes(reader.next4());
        return constant;
    };

    private final BiFunction<Integer, BytesReader, Constant> constantFloatParser = (id, reader) -> {
        ConstantFloat constant = new ConstantFloat();
        constant.setId(id);
        constant.setTag(reader.next1());
        constant.setBytes(reader.next4());
        return constant;
    };

    private final BiFunction<Integer, BytesReader, Constant> constantClassParser = (id, reader) -> {
        ConstantClass constant = new ConstantClass();
        constant.setId(id);
        constant.setTag(reader.next1());
        constant.setNameIndexBytes(reader.next2());
        return constant;
    };

    private final BiFunction<Integer, BytesReader, Constant> constantDoubleParser = (id, reader) -> {
        ConstantDouble constant = new ConstantDouble();
        constant.setId(id);
        constant.setTag(reader.next1());
        constant.setHighBytes(reader.next4());
        constant.setLowBytes(reader.next4());
        return constant;
    };

    private final BiFunction<Integer, BytesReader, Constant> constantDynamicParser = (id, reader) -> {
        ConstantDynamic constant = new ConstantDynamic();
        constant.setId(id);
        constant.setTag(reader.next1());
        constant.setBootstrapMethodAttrIndexBytes(reader.next2());
        constant.setNameAndTypeIndexBytes(reader.next2());
        return constant;
    };

    private final BiFunction<Integer, BytesReader, Constant> constantFieldrefParser = (id, reader) -> {
        ConstantFieldref constant = new ConstantFieldref();
        constant.setId(id);
        constant.setTag(reader.next1());
        constant.setClassIndexBytes(reader.next2());
        constant.setNameAndTypeIndexBytes(reader.next2());
        return constant;
    };

}
