package zlhywlf.javaasm.visitor.simple;

import java.util.Formatter;

import lombok.RequiredArgsConstructor;
import zlhywlf.javaasm.model.cp.ConstantClass;
import zlhywlf.javaasm.model.cp.ConstantDouble;
import zlhywlf.javaasm.model.cp.ConstantDynamic;
import zlhywlf.javaasm.model.cp.ConstantFieldref;
import zlhywlf.javaasm.model.cp.ConstantFloat;
import zlhywlf.javaasm.model.cp.ConstantInteger;
import zlhywlf.javaasm.model.cp.ConstantInterfaceMethodref;
import zlhywlf.javaasm.model.cp.ConstantInvokeDynamic;
import zlhywlf.javaasm.model.cp.ConstantLong;
import zlhywlf.javaasm.model.cp.ConstantMethodHandle;
import zlhywlf.javaasm.model.cp.ConstantMethodType;
import zlhywlf.javaasm.model.cp.ConstantMethodref;
import zlhywlf.javaasm.model.cp.ConstantModule;
import zlhywlf.javaasm.model.cp.ConstantNameAndType;
import zlhywlf.javaasm.model.cp.ConstantPackage;
import zlhywlf.javaasm.model.cp.ConstantString;
import zlhywlf.javaasm.model.cp.ConstantUtf8;
import zlhywlf.javaasm.util.ByteUtil;
import zlhywlf.javaasm.visitor.ConstantsVisitor;

@RequiredArgsConstructor
public class ConstantsSimpleVisitor implements ConstantsVisitor {

    private final ConstantsVisitor raw;

    @Override
    public void visitConstantClass(Formatter f, ConstantClass c) {
        raw.visitConstantClass(f, c);
        f.format("          Class --> #%03d%n", ByteUtil.toUnsignedInt(c.getNameIndexBytes()));
    }

    @Override
    public void visitConstantDouble(Formatter f, ConstantDouble c) {
        raw.visitConstantDouble(f, c);
        f.format("          Double = %e%n", ByteUtil.toDouble(ByteUtil.merge(c.getHighBytes(), c.getLowBytes())));
    }

    @Override
    public void visitConstantDynamic(Formatter f, ConstantDynamic c) {
        raw.visitConstantDynamic(f, c);
        f.format("          Dynamic bootstrapMethodAttr --> #%03d, nameAndType --> #%03d%n",
                ByteUtil.toUnsignedInt(c.getBootstrapMethodAttrIndexBytes()),
                ByteUtil.toUnsignedInt(c.getNameAndTypeIndexBytes()));
    }

    @Override
    public void visitConstantFieldref(Formatter f, ConstantFieldref c) {
        raw.visitConstantFieldref(f, c);
        f.format("          Fieldref class --> #%03d, nameAndType --> #%03d%n",
                ByteUtil.toUnsignedInt(c.getClassIndexBytes()),
                ByteUtil.toUnsignedInt(c.getNameAndTypeIndexBytes()));
    }

    @Override
    public void visitConstantFloat(Formatter f, ConstantFloat c) {
        raw.visitConstantFloat(f, c);
        f.format("          Float = %e%n", ByteUtil.toFloat(c.getBytes()));
    }

    @Override
    public void visitConstantInteger(Formatter f, ConstantInteger c) {
        raw.visitConstantInteger(f, c);
        f.format("          Integer = %d%n", ByteUtil.toInt(c.getBytes()));
    }

    @Override
    public void visitConstantInterfaceMethodref(Formatter f, ConstantInterfaceMethodref c) {
        raw.visitConstantInterfaceMethodref(f, c);
        f.format("          InterfaceMethodref class --> #%03d, nameAndType --> #%03d%n",
                ByteUtil.toUnsignedInt(c.getClassIndexBytes()),
                ByteUtil.toUnsignedInt(c.getNameAndTypeIndexBytes()));
    }

    @Override
    public void visitConstantInvokeDynamic(Formatter f, ConstantInvokeDynamic c) {
        raw.visitConstantInvokeDynamic(f, c);
        f.format("          InvokeDynamic bootstrapMethodAttr --> #%03d, nameAndType --> #%03d%n",
                ByteUtil.toUnsignedInt(c.getBootstrapMethodAttrIndexBytes()),
                ByteUtil.toUnsignedInt(c.getNameAndTypeIndexBytes()));
    }

    @Override
    public void visitConstantLong(Formatter f, ConstantLong c) {
        raw.visitConstantLong(f, c);
        f.format("          Long = %d%n", ByteUtil.toLong(ByteUtil.merge(c.getHighBytes(), c.getLowBytes())));
    }

    @Override
    public void visitConstantMethodHandle(Formatter f, ConstantMethodHandle c) {
        raw.visitConstantMethodHandle(f, c);
        f.format("          MethodHandle referenceKind = %d, reference --> #%03d%n",
                ByteUtil.toUnsignedInt(c.getReferenceKindBytes()),
                ByteUtil.toUnsignedInt(c.getReferenceIndexBytes()));
    }

    @Override
    public void visitConstantMethodType(Formatter f, ConstantMethodType c) {
        raw.visitConstantMethodType(f, c);
        f.format("          MethodType descriptor --> #%03d%n",
                ByteUtil.toUnsignedInt(c.getDescriptorIndexBytes()));
    }

    @Override
    public void visitConstantMethodref(Formatter f, ConstantMethodref c) {
        raw.visitConstantMethodref(f, c);
        f.format("          Methodref class --> #%03d, nameAndType --> #%03d%n",
                ByteUtil.toUnsignedInt(c.getClassIndexBytes()),
                ByteUtil.toUnsignedInt(c.getNameAndTypeIndexBytes()));
    }

    @Override
    public void visitConstantModule(Formatter f, ConstantModule c) {
        raw.visitConstantModule(f, c);
        f.format("          Module name --> #%03d%n",
                ByteUtil.toUnsignedInt(c.getNameIndexBytes()));
    }

    @Override
    public void visitConstantNameAndType(Formatter f, ConstantNameAndType c) {
        raw.visitConstantNameAndType(f, c);
        f.format("          NameAndType name --> #%03d, descriptor --> #%03d%n",
                ByteUtil.toUnsignedInt(c.getNameIndexBytes()), ByteUtil.toUnsignedInt(c.getDescriptorIndexBytes()));
    }

    @Override
    public void visitConstantPackage(Formatter f, ConstantPackage c) {
        raw.visitConstantPackage(f, c);
        f.format("          Package name --> #%03d%n",
                ByteUtil.toUnsignedInt(c.getNameIndexBytes()));
    }

    @Override
    public void visitConstantString(Formatter f, ConstantString c) {
        raw.visitConstantString(f, c);
        f.format("          String --> #%03d%n",
                ByteUtil.toUnsignedInt(c.getStringIndexBytes()));
    }

    @Override
    public void visitConstantUtf8(Formatter f, ConstantUtf8 c) {
        raw.visitConstantUtf8(f, c);
        f.format("          Utf8 length = %d, bytes = %s%n",
                ByteUtil.toUnsignedInt(c.getLengthBytes()), ByteUtil.toUtf8(c.getBytes()));
    }

}
