package zlhywlf.javaasm.visitor;

import java.util.Formatter;

import zlhywlf.javaasm.model.Attribute;
import zlhywlf.javaasm.model.Constant;
import zlhywlf.javaasm.model.Member;
import zlhywlf.javaasm.model.Node;

public interface ClassFileVisitor {

    void visitMagic(Formatter f, Node n);

    void visitMinorVersion(Formatter f, Node n);

    void visitMajorVersion(Formatter f, Node n);

    void visitConstantPoolCount(Formatter f, Node n);

    void visitConstantPool(Formatter f, Constant[] n);

    void visitAccessFlags(Formatter f, Node n);

    void visitThisClass(Formatter f, Node n);

    void visitSuperClass(Formatter f, Node n);

    void visitInterfacesCount(Formatter f, Node n);

    void visitInterfaces(Formatter f, Node[] n);

    void visitFieldsCount(Formatter f, Node n);

    void visitFields(Formatter f, Member[] n);

    void visitMethodsCount(Formatter f, Node n);

    void visitMethods(Formatter f, Member[] n);

    void visitAttributesCount(Formatter f, Node n);

    void visitAttributes(Formatter f, Attribute[] n);

}