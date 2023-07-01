package zlhywlf.javaasm.builder.visitor;

import java.util.Formatter;
import java.util.HashMap;
import java.util.Map;
import java.util.function.BiConsumer;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import zlhywlf.javaasm.cnst.ClassFileConst;
import zlhywlf.javaasm.model.Constant;
import zlhywlf.javaasm.model.cp.*;
import zlhywlf.javaasm.visitor.ConstantsVisitor;

/**
 * 常量池格式化策略构建
 * 
 * @author zlhywlf
 */
@RequiredArgsConstructor
public class ConstantsVisitorStrategyBuilder {

    @NonNull
    private ConstantsVisitor visitor;

    public Map<Byte, BiConsumer<Formatter, Constant>> build() {
        Map<Byte, BiConsumer<Formatter, Constant>> strategyMap = new HashMap<>(17);
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

    private BiConsumer<Formatter, Constant> constantUtf8 = (f, c) -> {
        visitor.visitConstantUtf8(f, cast(c, ConstantUtf8.class));
    };

    private BiConsumer<Formatter, Constant> constantString = (f, c) -> {
        visitor.visitConstantString(f, cast(c, ConstantString.class));
    };

    private BiConsumer<Formatter, Constant> constantPackage = (f, c) -> {
        visitor.visitConstantPackage(f, cast(c, ConstantPackage.class));
    };

    private BiConsumer<Formatter, Constant> constantNameAndType = (f, c) -> {
        visitor.visitConstantNameAndType(f, cast(c, ConstantNameAndType.class));
    };

    private BiConsumer<Formatter, Constant> constantModule = (f, c) -> {
        visitor.visitConstantModule(f, cast(c, ConstantModule.class));
    };

    private BiConsumer<Formatter, Constant> constantMethodType = (f, c) -> {
        visitor.visitConstantMethodType(f, cast(c, ConstantMethodType.class));
    };

    private BiConsumer<Formatter, Constant> constantMethodref = (f, c) -> {
        visitor.visitConstantMethodref(f, cast(c, ConstantMethodref.class));
    };

    private BiConsumer<Formatter, Constant> constantMethodHandle = (f, c) -> {
        visitor.visitConstantMethodHandle(f, cast(c, ConstantMethodHandle.class));
    };

    private BiConsumer<Formatter, Constant> constantLong = (f, c) -> {
        visitor.visitConstantLong(f, cast(c, ConstantLong.class));
    };

    private BiConsumer<Formatter, Constant> constantInvokeDynamic = (f, c) -> {
        visitor.visitConstantInvokeDynamic(f, cast(c, ConstantInvokeDynamic.class));
    };

    private BiConsumer<Formatter, Constant> constantInterfaceMethodref = (f, c) -> {
        visitor.visitConstantInterfaceMethodref(f, cast(c, ConstantInterfaceMethodref.class));
    };

    private BiConsumer<Formatter, Constant> constantInteger = (f, c) -> {
        visitor.visitConstantInteger(f, cast(c, ConstantInteger.class));
    };

    private BiConsumer<Formatter, Constant> constantFloat = (f, c) -> {
        visitor.visitConstantFloat(f, cast(c, ConstantFloat.class));
    };

    private BiConsumer<Formatter, Constant> constantFieldref = (f, c) -> {
        visitor.visitConstantFieldref(f, cast(c, ConstantFieldref.class));
    };

    private BiConsumer<Formatter, Constant> constantDynamic = (f, c) -> {
        visitor.visitConstantDynamic(f, cast(c, ConstantDynamic.class));
    };

    private BiConsumer<Formatter, Constant> constantDouble = (f, c) -> {
        visitor.visitConstantDouble(f, cast(c, ConstantDouble.class));
    };

    private BiConsumer<Formatter, Constant> constantClass = (f, c) -> {
        visitor.visitConstantClass(f, cast(c, ConstantClass.class));
    };

    @SuppressWarnings("unchecked")
    private <T> T cast(Constant c, Class<T> act) {
        if (c.getClass().equals(act)) {
            return (T) c;
        }
        throw new RuntimeException("类型不匹配");
    }

}
