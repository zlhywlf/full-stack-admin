package zlhywlf.javaasm.classfile.visitor;

import zlhywlf.javaasm.classfile.node.Node;

public interface Visitor {

    void visitMagic(Node obj);

    void visitMinorVersion(Node obj);

    void visitMajorVersion(Node obj);

    void visitConstantPoolCount(Node obj);

    void visitConstantPool(Node obj);

}
