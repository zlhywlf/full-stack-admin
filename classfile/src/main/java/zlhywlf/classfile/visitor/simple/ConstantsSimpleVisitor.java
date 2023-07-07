package zlhywlf.classfile.visitor.simple;

import static zlhywlf.classfile.util.FormatUtil.*;

import java.util.Formatter;

import lombok.RequiredArgsConstructor;
import zlhywlf.classfile.model.cp.ConstantClass;
import zlhywlf.classfile.model.cp.ConstantDouble;
import zlhywlf.classfile.model.cp.ConstantDynamic;
import zlhywlf.classfile.model.cp.ConstantFieldref;
import zlhywlf.classfile.model.cp.ConstantFloat;
import zlhywlf.classfile.model.cp.ConstantInteger;
import zlhywlf.classfile.model.cp.ConstantInterfaceMethodref;
import zlhywlf.classfile.model.cp.ConstantInvokeDynamic;
import zlhywlf.classfile.model.cp.ConstantLong;
import zlhywlf.classfile.model.cp.ConstantMethodHandle;
import zlhywlf.classfile.model.cp.ConstantMethodType;
import zlhywlf.classfile.model.cp.ConstantMethodref;
import zlhywlf.classfile.model.cp.ConstantModule;
import zlhywlf.classfile.model.cp.ConstantNameAndType;
import zlhywlf.classfile.model.cp.ConstantPackage;
import zlhywlf.classfile.model.cp.ConstantString;
import zlhywlf.classfile.model.cp.ConstantUtf8;
import zlhywlf.classfile.util.ByteUtil;
import zlhywlf.classfile.visitor.ConstantsVisitor;

@RequiredArgsConstructor
public class ConstantsSimpleVisitor implements ConstantsVisitor {

    private final ConstantsVisitor raw;

    @Override
    public void visitConstantClass(Formatter f, ConstantClass c) {
        raw.visitConstantClass(f, c);
        formatConstant(f, c, "Class");
        formatIndex(f, "nameIndex", c.getNameIndexBytes());
    }

    @Override
    public void visitConstantDouble(Formatter f, ConstantDouble c) {
        raw.visitConstantDouble(f, c);
        formatConstant(f, c, "Double");
        byte[] bytes = ByteUtil.merge(c.getHighBytes(), c.getLowBytes());
        formatNumber(f, bytes, ByteUtil.toDouble(bytes));
    }

    @Override
    public void visitConstantDynamic(Formatter f, ConstantDynamic c) {
        raw.visitConstantDynamic(f, c);
        formatConstant(f, c, "Dynamic");
        formatIndex(f, "bootstrapMethodAttr", c.getBootstrapMethodAttrIndexBytes());
        formatIndex(f, "nameAndType", c.getNameAndTypeIndexBytes());
    }

    @Override
    public void visitConstantFieldref(Formatter f, ConstantFieldref c) {
        raw.visitConstantFieldref(f, c);
        formatConstant(f, c, "Fieldref");
        formatIndex(f, "class", c.getClassIndexBytes());
        formatIndex(f, "nameAndType", c.getNameAndTypeIndexBytes());
    }

    @Override
    public void visitConstantFloat(Formatter f, ConstantFloat c) {
        raw.visitConstantFloat(f, c);
        formatConstant(f, c, "Float");
        formatNumber(f, c.getBytes(), ByteUtil.toFloat(c.getBytes()));
    }

    @Override
    public void visitConstantInteger(Formatter f, ConstantInteger c) {
        raw.visitConstantInteger(f, c);
        formatConstant(f, c, "Integer");
        formatNumber(f, c.getBytes(), ByteUtil.toInt(c.getBytes()));
    }

    @Override
    public void visitConstantInterfaceMethodref(Formatter f, ConstantInterfaceMethodref c) {
        raw.visitConstantInterfaceMethodref(f, c);
        formatConstant(f, c, "InterfaceMethodref");
        formatIndex(f, "class", c.getClassIndexBytes());
        formatIndex(f, "nameAndType", c.getNameAndTypeIndexBytes());
    }

    @Override
    public void visitConstantInvokeDynamic(Formatter f, ConstantInvokeDynamic c) {
        raw.visitConstantInvokeDynamic(f, c);
        formatConstant(f, c, "InvokeDynamic");
        formatIndex(f, "bootstrapMethodAttr", c.getBootstrapMethodAttrIndexBytes());
        formatIndex(f, "nameAndType", c.getNameAndTypeIndexBytes());
    }

    @Override
    public void visitConstantLong(Formatter f, ConstantLong c) {
        raw.visitConstantLong(f, c);
        formatConstant(f, c, "Long");
        byte[] bytes = ByteUtil.merge(c.getHighBytes(), c.getLowBytes());
        formatNumber(f, bytes, ByteUtil.toLong(bytes));
    }

    @Override
    public void visitConstantMethodHandle(Formatter f, ConstantMethodHandle c) {
        raw.visitConstantMethodHandle(f, c);
        formatConstant(f, c, "MethodHandle");
        formatNumber(f, "referenceKind", c.getReferenceKindBytes(), ByteUtil.toUnsignedInt(c.getReferenceKindBytes()));
        formatIndex(f, "reference", c.getReferenceIndexBytes());
    }

    @Override
    public void visitConstantMethodType(Formatter f, ConstantMethodType c) {
        raw.visitConstantMethodType(f, c);
        formatConstant(f, c, "MethodType");
        formatIndex(f, "descriptor", c.getDescriptorIndexBytes());
    }

    @Override
    public void visitConstantMethodref(Formatter f, ConstantMethodref c) {
        raw.visitConstantMethodref(f, c);
        formatConstant(f, c, "Methodref");
        formatIndex(f, "class", c.getClassIndexBytes());
        formatIndex(f, "nameAndType", c.getNameAndTypeIndexBytes());
    }

    @Override
    public void visitConstantModule(Formatter f, ConstantModule c) {
        raw.visitConstantModule(f, c);
        formatConstant(f, c, "Module");
        formatIndex(f, "nameIndex", c.getNameIndexBytes());
    }

    @Override
    public void visitConstantNameAndType(Formatter f, ConstantNameAndType c) {
        raw.visitConstantNameAndType(f, c);
        formatConstant(f, c, "NameAndType");
        formatIndex(f, "nameIndex", c.getNameIndexBytes());
        formatIndex(f, "descriptorIndex", c.getDescriptorIndexBytes());
    }

    @Override
    public void visitConstantPackage(Formatter f, ConstantPackage c) {
        raw.visitConstantPackage(f, c);
        formatConstant(f, c, "Package");
        formatIndex(f, "name", c.getNameIndexBytes());
    }

    @Override
    public void visitConstantString(Formatter f, ConstantString c) {
        raw.visitConstantString(f, c);
        formatConstant(f, c, "String");
        formatIndex(f, "index", c.getStringIndexBytes());
    }

    @Override
    public void visitConstantUtf8(Formatter f, ConstantUtf8 c) {
        raw.visitConstantUtf8(f, c);
        formatConstant(f, c, "Utf8");
        formatNumber(f, "length", c.getLengthBytes(), ByteUtil.toUnsignedInt(c.getLengthBytes()));
        formatString(f, "bytes", c.getBytes());
    }

}
