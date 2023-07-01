package zlhywlf.javaasm.visitor;

import java.util.Formatter;

import zlhywlf.javaasm.model.cp.*;

public interface ConstantsVisitor {

    void visitConstantClass(Formatter f, ConstantClass c);

    void visitConstantDouble(Formatter f, ConstantDouble c);

    void visitConstantDynamic(Formatter f, ConstantDynamic c);

    void visitConstantFieldref(Formatter f, ConstantFieldref c);

    void visitConstantFloat(Formatter f, ConstantFloat c);

    void visitConstantInteger(Formatter f, ConstantInteger c);

    void visitConstantInterfaceMethodref(Formatter f, ConstantInterfaceMethodref c);

    void visitConstantInvokeDynamic(Formatter f, ConstantInvokeDynamic c);

    void visitConstantLong(Formatter f, ConstantLong c);

    void visitConstantMethodHandle(Formatter f, ConstantMethodHandle c);

    void visitConstantMethodType(Formatter f, ConstantMethodType c);

    void visitConstantMethodref(Formatter f, ConstantMethodref c);

    void visitConstantModule(Formatter f, ConstantModule c);

    void visitConstantNameAndType(Formatter f, ConstantNameAndType c);

    void visitConstantPackage(Formatter f, ConstantPackage c);

    void visitConstantString(Formatter f, ConstantString c);

    void visitConstantUtf8(Formatter f, ConstantUtf8 c);

}
