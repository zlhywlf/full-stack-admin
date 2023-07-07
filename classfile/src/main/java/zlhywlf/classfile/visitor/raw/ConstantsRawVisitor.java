package zlhywlf.classfile.visitor.raw;

import java.util.Formatter;

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
import zlhywlf.classfile.util.FormatUtil;
import zlhywlf.classfile.visitor.ConstantsVisitor;

public class ConstantsRawVisitor implements ConstantsVisitor {

    @Override
    public void visitConstantClass(Formatter f, ConstantClass c) {
        FormatUtil.formatItem(f, c.getId(), c.getTag(), c.getNameIndexBytes());
    }

    @Override
    public void visitConstantDouble(Formatter f, ConstantDouble c) {
        FormatUtil.formatItem(f, c.getId(), c.getTag(), c.getHighBytes(), c.getLowBytes());
    }

    @Override
    public void visitConstantDynamic(Formatter f, ConstantDynamic c) {
        FormatUtil.formatItem(f, c.getId(), c.getTag(), c.getBootstrapMethodAttrIndexBytes(),
                c.getNameAndTypeIndexBytes());
    }

    @Override
    public void visitConstantFieldref(Formatter f, ConstantFieldref c) {
        FormatUtil.formatItem(f, c.getId(), c.getTag(), c.getClassIndexBytes(), c.getNameAndTypeIndexBytes());
    }

    @Override
    public void visitConstantFloat(Formatter f, ConstantFloat c) {
        FormatUtil.formatItem(f, c.getId(), c.getTag(), c.getBytes());
    }

    @Override
    public void visitConstantInteger(Formatter f, ConstantInteger c) {
        FormatUtil.formatItem(f, c.getId(), c.getTag(), c.getBytes());
    }

    @Override
    public void visitConstantInterfaceMethodref(Formatter f, ConstantInterfaceMethodref c) {
        FormatUtil.formatItem(f, c.getId(), c.getTag(), c.getClassIndexBytes(), c.getNameAndTypeIndexBytes());
    }

    @Override
    public void visitConstantInvokeDynamic(Formatter f, ConstantInvokeDynamic c) {
        FormatUtil.formatItem(f, c.getId(), c.getTag(), c.getBootstrapMethodAttrIndexBytes(),
                c.getNameAndTypeIndexBytes());
    }

    @Override
    public void visitConstantLong(Formatter f, ConstantLong c) {
        FormatUtil.formatItem(f, c.getId(), c.getTag(), c.getHighBytes(), c.getLowBytes());
    }

    @Override
    public void visitConstantMethodHandle(Formatter f, ConstantMethodHandle c) {
        FormatUtil.formatItem(f, c.getId(), c.getTag(), c.getReferenceKindBytes(), c.getReferenceIndexBytes());
    }

    @Override
    public void visitConstantMethodType(Formatter f, ConstantMethodType c) {
        FormatUtil.formatItem(f, c.getId(), c.getTag(), c.getDescriptorIndexBytes());
    }

    @Override
    public void visitConstantMethodref(Formatter f, ConstantMethodref c) {
        FormatUtil.formatItem(f, c.getId(), c.getTag(), c.getClassIndexBytes(), c.getNameAndTypeIndexBytes());
    }

    @Override
    public void visitConstantModule(Formatter f, ConstantModule c) {
        FormatUtil.formatItem(f, c.getId(), c.getTag(), c.getNameIndexBytes());
    }

    @Override
    public void visitConstantNameAndType(Formatter f, ConstantNameAndType c) {
        FormatUtil.formatItem(f, c.getId(), c.getTag(), c.getNameIndexBytes(), c.getDescriptorIndexBytes());
    }

    @Override
    public void visitConstantPackage(Formatter f, ConstantPackage c) {
        FormatUtil.formatItem(f, c.getId(), c.getTag(), c.getNameIndexBytes());
    }

    @Override
    public void visitConstantString(Formatter f, ConstantString c) {
        FormatUtil.formatItem(f, c.getId(), c.getTag(), c.getStringIndexBytes());
    }

    @Override
    public void visitConstantUtf8(Formatter f, ConstantUtf8 c) {
        FormatUtil.formatItem(f, c.getId(), c.getTag(), c.getLengthBytes(), c.getBytes());
    }

}
