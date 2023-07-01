package zlhywlf.javaasm.visitor.raw;

import java.util.Formatter;

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
import zlhywlf.javaasm.util.HexUtil;
import zlhywlf.javaasm.visitor.ConstantsVisitor;

public class ConstantsRawVisitor implements ConstantsVisitor {

    private void visit(Formatter f, int id, byte[]... bytes) {
        f.format("    |%03d| %s%n", id, HexUtil.format(ByteUtil.merge(bytes)));
    }

    @Override
    public void visitConstantClass(Formatter f, ConstantClass c) {
        visit(f, c.getId(), c.getTag(), c.getNameIndexBytes());
    }

    @Override
    public void visitConstantDouble(Formatter f, ConstantDouble c) {
        visit(f, c.getId(), c.getTag(), c.getHighBytes(), c.getLowBytes());
    }

    @Override
    public void visitConstantDynamic(Formatter f, ConstantDynamic c) {
        visit(f, c.getId(), c.getTag(), c.getBootstrapMethodAttrIndexBytes(), c.getNameAndTypeIndexBytes());
    }

    @Override
    public void visitConstantFieldref(Formatter f, ConstantFieldref c) {
        visit(f, c.getId(), c.getTag(), c.getClassIndexBytes(), c.getNameAndTypeIndexBytes());
    }

    @Override
    public void visitConstantFloat(Formatter f, ConstantFloat c) {
        visit(f, c.getId(), c.getTag(), c.getBytes());
    }

    @Override
    public void visitConstantInteger(Formatter f, ConstantInteger c) {
        visit(f, c.getId(), c.getTag(), c.getBytes());
    }

    @Override
    public void visitConstantInterfaceMethodref(Formatter f, ConstantInterfaceMethodref c) {
        visit(f, c.getId(), c.getTag(), c.getClassIndexBytes(), c.getNameAndTypeIndexBytes());
    }

    @Override
    public void visitConstantInvokeDynamic(Formatter f, ConstantInvokeDynamic c) {
        visit(f, c.getId(), c.getTag(), c.getBootstrapMethodAttrIndexBytes(), c.getNameAndTypeIndexBytes());
    }

    @Override
    public void visitConstantLong(Formatter f, ConstantLong c) {
        visit(f, c.getId(), c.getTag(), c.getHighBytes(), c.getLowBytes());
    }

    @Override
    public void visitConstantMethodHandle(Formatter f, ConstantMethodHandle c) {
        visit(f, c.getId(), c.getTag(), c.getReferenceKindBytes(), c.getReferenceIndexBytes());
    }

    @Override
    public void visitConstantMethodType(Formatter f, ConstantMethodType c) {
        visit(f, c.getId(), c.getTag(), c.getDescriptorIndexBytes());
    }

    @Override
    public void visitConstantMethodref(Formatter f, ConstantMethodref c) {
        visit(f, c.getId(), c.getTag(), c.getClassIndexBytes(), c.getNameAndTypeIndexBytes());
    }

    @Override
    public void visitConstantModule(Formatter f, ConstantModule c) {
        visit(f, c.getId(), c.getTag(), c.getNameIndexBytes());
    }

    @Override
    public void visitConstantNameAndType(Formatter f, ConstantNameAndType c) {
        visit(f, c.getId(), c.getTag(), c.getNameIndexBytes(), c.getDescriptorIndexBytes());
    }

    @Override
    public void visitConstantPackage(Formatter f, ConstantPackage c) {
        visit(f, c.getId(), c.getTag(), c.getNameIndexBytes());
    }

    @Override
    public void visitConstantString(Formatter f, ConstantString c) {
        visit(f, c.getId(), c.getTag(), c.getStringIndexBytes());
    }

    @Override
    public void visitConstantUtf8(Formatter f, ConstantUtf8 c) {
        visit(f, c.getId(), c.getTag(), c.getLengthBytes(), c.getBytes());
    }

}
