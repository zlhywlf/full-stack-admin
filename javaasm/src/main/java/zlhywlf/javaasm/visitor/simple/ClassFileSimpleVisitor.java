package zlhywlf.javaasm.visitor.simple;

import java.util.Formatter;
import java.util.function.BiConsumer;

import lombok.RequiredArgsConstructor;
import zlhywlf.javaasm.model.Attribute;
import zlhywlf.javaasm.model.Constant;
import zlhywlf.javaasm.model.Interface;
import zlhywlf.javaasm.model.Member;
import zlhywlf.javaasm.model.Node;
import zlhywlf.javaasm.util.ByteUtil;
import zlhywlf.javaasm.visitor.ClassFileVisitor;

@RequiredArgsConstructor
public class ClassFileSimpleVisitor implements ClassFileVisitor {

    private final ClassFileVisitor raw;
    private BiConsumer<Formatter, Node> getValue = (f, n) -> {
        f.format("   %d%n", ByteUtil.toUnsignedInt(n.getBytes()));
    };

    @Override
    public void visitMagic(Formatter f, Node n) {
        raw.visitMagic(f, n);
        f.format("   咖啡馆宝贝%n");
    }

    @Override
    public void visitMinorVersion(Formatter f, Node n) {
        raw.visitMinorVersion(f, n);
        getValue.accept(f, n);
    }

    @Override
    public void visitMajorVersion(Formatter f, Node n) {
        raw.visitMajorVersion(f, n);
        getValue.accept(f, n);
    }

    @Override
    public void visitConstantPoolCount(Formatter f, Node n) {
        raw.visitConstantPoolCount(f, n);
        getValue.accept(f, n);
    }

    @Override
    public void visitConstantPool(Formatter f, Constant[] n) {
        raw.visitConstantPool(f, n);
    }

    @Override
    public void visitAccessFlags(Formatter f, Node n) {
        raw.visitAccessFlags(f, n);
        f.format("   0b%s%n", Integer.toBinaryString(1 << 16 | ByteUtil.toUnsignedInt(n.getBytes())).substring(1));
    }

    @Override
    public void visitThisClass(Formatter f, Node n) {
        raw.visitThisClass(f, n);
        f.format("   --> #%03d%n", ByteUtil.toUnsignedInt(n.getBytes()));
    }

    @Override
    public void visitSuperClass(Formatter f, Node n) {
        raw.visitSuperClass(f, n);
        f.format("   --> #%03d%n", ByteUtil.toUnsignedInt(n.getBytes()));
    }

    @Override
    public void visitInterfacesCount(Formatter f, Node n) {
        raw.visitInterfacesCount(f, n);
        getValue.accept(f, n);
    }

    @Override
    public void visitInterfaces(Formatter f, Interface[] n) {
        raw.visitInterfaces(f, n);
    }

    @Override
    public void visitFieldsCount(Formatter f, Node n) {
        raw.visitFieldsCount(f, n);
        getValue.accept(f, n);
    }

    @Override
    public void visitFields(Formatter f, Member[] n) {
        raw.visitFields(f, n);
    }

    @Override
    public void visitMethodsCount(Formatter f, Node n) {
        raw.visitMethodsCount(f, n);
        getValue.accept(f, n);
    }

    @Override
    public void visitMethods(Formatter f, Member[] n) {
        raw.visitMethods(f, n);
    }

    @Override
    public void visitAttributesCount(Formatter f, Node n) {
        raw.visitAttributesCount(f, n);
        getValue.accept(f, n);
    }

    @Override
    public void visitAttributes(Formatter f, Attribute[] n) {
        raw.visitAttributes(f, n);
    }

}
