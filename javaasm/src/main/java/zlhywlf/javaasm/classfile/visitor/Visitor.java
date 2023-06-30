package zlhywlf.javaasm.classfile.visitor;

import zlhywlf.javaasm.classfile.node.Node;

public interface Visitor {

    void visitMagic(Node obj);

    void visitMinorVersion(Node obj);

    void visitMajorVersion(Node obj);

    void visitConstantPoolCount(Node obj);

    void visitConstantPool(Node obj);

    void visitAccessFlags(Node obj);

    void visitThisClass(Node obj);

    void visitSuperClass(Node obj);

    void visitInterfacesCount(Node obj);

    void visitInterfaces(Node obj);

    void visitFieldsCount(Node obj);

    void visitFields(Node obj);

    void visitFieldInfo(Node obj);

    void visitAttributeInfo(Node obj);

    void visitMethodsCount(Node obj);

    void visitMethods(Node obj);

    void visitAttributesCount(Node obj);

    void visitAttributes(Node obj);

    void visitConstant(Node obj);

    default void visitConstantClass(Node obj) {
        visitConstant(obj);
    }

    default void visitConstantUtf8(Node obj) {
        visitConstant(obj);
    }

    default void visitConstantMethodref(Node obj) {
        visitConstant(obj);
    }

    default void visitConstantNameAndType(Node obj) {
        visitConstant(obj);
    }

    default void visitConstantInteger(Node obj) {
        visitConstant(obj);
    }

    default void visitConstantFieldref(Node obj) {
        visitConstant(obj);
    }

    default void visitConstantString(Node obj) {
        visitConstant(obj);
    }

    default void visitConstantDouble(Node obj) {
        visitConstant(obj);
    }

    default void visitConstantFloat(Node obj) {
        visitConstant(obj);
    }

    default void visitConstantLong(Node obj) {
        visitConstant(obj);
    }

    default void visitConstantInterfaceMethodref(Node obj) {
        visitConstant(obj);
    }

    default void visitConstantInvokeDynamic(Node obj) {
        visitConstant(obj);
    }

    default void visitConstantMethodHandle(Node obj) {
        visitConstant(obj);
    }

    default void visitConstantMethodType(Node obj) {
        visitConstant(obj);
    }

    default void visitConstantModule(Node obj) {
        visitConstant(obj);
    }

    default void visitConstantPackage(Node obj) {
        visitConstant(obj);
    }

    default void visitConstantDynamic(Node obj) {
        visitConstant(obj);
    }

}
