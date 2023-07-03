package zlhywlf.javaasm.visitor.simple;

import java.util.Formatter;

import lombok.RequiredArgsConstructor;
import zlhywlf.javaasm.model.Attribute;
import zlhywlf.javaasm.model.Constant;
import zlhywlf.javaasm.model.Interface;
import zlhywlf.javaasm.model.Member;
import zlhywlf.javaasm.model.Node;
import zlhywlf.javaasm.util.ByteUtil;
import zlhywlf.javaasm.util.FormatUtil;
import zlhywlf.javaasm.visitor.ClassFileVisitor;

@RequiredArgsConstructor
public class ClassFileSimpleVisitor implements ClassFileVisitor {

    private final ClassFileVisitor raw;

    @Override
    public void visitMagic(Formatter f, Node n) {
        raw.visitMagic(f, n);
        FormatUtil.formatString(f, "Magic", "咖啡馆宝贝");
    }

    @Override
    public void visitMinorVersion(Formatter f, Node n) {
        raw.visitMinorVersion(f, n);
        FormatUtil.formatString(f, "MinorVersion", String.valueOf(ByteUtil.toUnsignedInt(n.getBytes())));
    }

    @Override
    public void visitMajorVersion(Formatter f, Node n) {
        raw.visitMajorVersion(f, n);
        FormatUtil.formatString(f, "MajorVersion", String.valueOf(ByteUtil.toUnsignedInt(n.getBytes())));
    }

    @Override
    public void visitConstantPoolCount(Formatter f, Node n) {
        raw.visitConstantPoolCount(f, n);
        FormatUtil.formatString(f, "ConstantPoolCount", String.valueOf(ByteUtil.toUnsignedInt(n.getBytes())));
    }

    @Override
    public void visitConstantPool(Formatter f, Constant[] n) {
        raw.visitConstantPool(f, n);
    }

    @Override
    public void visitAccessFlags(Formatter f, Node n) {
        raw.visitAccessFlags(f, n);
        FormatUtil.formatBinary(f, "AccessFlags", n.getBytes());
    }

    @Override
    public void visitThisClass(Formatter f, Node n) {
        raw.visitThisClass(f, n);
        FormatUtil.formatIndex(f, "ThisClass", n.getBytes());
    }

    @Override
    public void visitSuperClass(Formatter f, Node n) {
        raw.visitSuperClass(f, n);
        FormatUtil.formatIndex(f, "SuperClass", n.getBytes());
    }

    @Override
    public void visitInterfacesCount(Formatter f, Node n) {
        raw.visitInterfacesCount(f, n);
        FormatUtil.formatString(f, "InterfacesCount", String.valueOf(ByteUtil.toUnsignedInt(n.getBytes())));
    }

    @Override
    public void visitInterfaces(Formatter f, Interface[] n) {
        raw.visitInterfaces(f, n);
    }

    @Override
    public void visitFieldsCount(Formatter f, Node n) {
        raw.visitFieldsCount(f, n);
        FormatUtil.formatString(f, "FieldsCount", String.valueOf(ByteUtil.toUnsignedInt(n.getBytes())));
    }

    @Override
    public void visitFields(Formatter f, Member[] n) {
        raw.visitFields(f, n);
    }

    @Override
    public void visitMethodsCount(Formatter f, Node n) {
        raw.visitMethodsCount(f, n);
        FormatUtil.formatString(f, "MethodsCount", String.valueOf(ByteUtil.toUnsignedInt(n.getBytes())));
    }

    @Override
    public void visitMethods(Formatter f, Member[] n) {
        raw.visitMethods(f, n);
    }

    @Override
    public void visitAttributesCount(Formatter f, Node n) {
        raw.visitAttributesCount(f, n);
        FormatUtil.formatString(f, "AttributesCount", String.valueOf(ByteUtil.toUnsignedInt(n.getBytes())));
    }

    @Override
    public void visitAttributes(Formatter f, Attribute[] n) {
        raw.visitAttributes(f, n);
    }

}
