package zlhywlf.javaasm.visitor.raw;

import java.util.Formatter;
import java.util.Map;
import java.util.function.BiConsumer;

import lombok.RequiredArgsConstructor;
import zlhywlf.javaasm.model.Attribute;
import zlhywlf.javaasm.model.Constant;
import zlhywlf.javaasm.model.Interface;
import zlhywlf.javaasm.model.Member;
import zlhywlf.javaasm.model.Node;
import zlhywlf.javaasm.util.ByteUtil;
import zlhywlf.javaasm.util.HexUtil;
import zlhywlf.javaasm.visitor.ClassFileVisitor;

@RequiredArgsConstructor
public class ClassFileRawVisitor implements ClassFileVisitor {

    private final Map<Byte, BiConsumer<Formatter, Constant>> constantStrategyMap;
    private final BiConsumer<Formatter, Interface> interfaceRawVisitor;
    private final BiConsumer<Formatter, Member> memberRawVisitor;
    private final BiConsumer<Formatter, Attribute> attributeRawVisitor;

    protected void visitNode(String name, Formatter f, Node n) {
        f.format("%s %s%n", name, HexUtil.format(n.getBytes()));
    }

    protected void visitMembers(String name, Formatter f, Member[] n) {
        f.format("%s:%n", name);
        for (Member m : n) {
            memberRawVisitor.accept(f, m);
        }
    }

    @Override
    public void visitMagic(Formatter f, Node n) {
        visitNode("u4 magic:", f, n);
    }

    @Override
    public void visitMinorVersion(Formatter f, Node n) {
        visitNode("u2 minor_version:", f, n);
    }

    @Override
    public void visitMajorVersion(Formatter f, Node n) {
        visitNode("u2 major_version:", f, n);
    }

    @Override
    public void visitConstantPoolCount(Formatter f, Node n) {
        visitNode("u2 constant_pool_count:", f, n);
    }

    @Override
    public void visitConstantPool(Formatter f, Constant[] n) {
        f.format("%s:%n", "cp_info constant_pool[constant_pool_count-1]");
        for (Constant c : n) {
            if (c != null) {
                byte tag = (byte) ByteUtil.toUnsignedInt(c.getTag());
                constantStrategyMap.getOrDefault(tag, (a, b) -> {
                    throw new RuntimeException(String.format("无效常量池类型: %d", tag));
                }).accept(f, c);
            }
        }
    }

    @Override
    public void visitAccessFlags(Formatter f, Node n) {
        visitNode("u2 access_flags:", f, n);
    }

    @Override
    public void visitThisClass(Formatter f, Node n) {
        visitNode("u2 this_class:", f, n);
    }

    @Override
    public void visitSuperClass(Formatter f, Node n) {
        visitNode("u2 super_class:", f, n);
    }

    @Override
    public void visitInterfacesCount(Formatter f, Node n) {
        visitNode("u2 interfaces_count:", f, n);
    }

    @Override
    public void visitInterfaces(Formatter f, Interface[] n) {
        f.format("%s:%n", "u2 interfaces[interfaces_count]");
        for (Interface i : n) {
            interfaceRawVisitor.accept(f, i);
        }
    }

    @Override
    public void visitFieldsCount(Formatter f, Node n) {
        visitNode("u2 fields_count:", f, n);
    }

    @Override
    public void visitFields(Formatter f, Member[] n) {
        visitMembers("field_info fields[fields_count]", f, n);
    }

    @Override
    public void visitMethodsCount(Formatter f, Node n) {
        visitNode("u2 methods_count:", f, n);
    }

    @Override
    public void visitMethods(Formatter f, Member[] n) {
        visitMembers("method_info methods[methods_count]", f, n);
    }

    @Override
    public void visitAttributesCount(Formatter f, Node n) {
        visitNode("u2 attributes_count:", f, n);
    }

    @Override
    public void visitAttributes(Formatter f, Attribute[] n) {
        f.format("%s:%n", "attribute_info attributes[attributes_count]");
        for (Attribute a : n) {
            attributeRawVisitor.accept(f, a);
        }
    }

}
