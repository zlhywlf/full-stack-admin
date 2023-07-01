package zlhywlf.javaasm.visitor.raw;

import java.util.Formatter;
import java.util.Map;
import java.util.function.BiConsumer;

import lombok.RequiredArgsConstructor;
import zlhywlf.javaasm.model.Attribute;
import zlhywlf.javaasm.model.Constant;
import zlhywlf.javaasm.model.Member;
import zlhywlf.javaasm.model.Node;
import zlhywlf.javaasm.util.ByteUtil;
import zlhywlf.javaasm.util.HexUtil;
import zlhywlf.javaasm.visitor.ClassFileVisitor;

@RequiredArgsConstructor
public class ClassFileRawVisitor implements ClassFileVisitor {

    private final Map<Byte, BiConsumer<Formatter, Constant>> strategyMap;

    private void visit(String name, Formatter f, byte[] bytes) {
        visit(name, f, bytes, false);
    }

    private void visit(String name, Formatter f, byte[] bytes, boolean isId) {
        if (isId) {
            f.format("%s: %s --> cp[%03d]%n", name, HexUtil.format(bytes),
                    ByteUtil.toUnsignedInt(bytes));
            return;
        }
        f.format("%s: %s --> %d%n", name, HexUtil.format(bytes),
                ByteUtil.toUnsignedInt(bytes));
    }

    private void visitMember(String name, Formatter f, Member[] n) {
        f.format("%s:%n", name);
        for (int i = 0; i < n.length; i++) {
            Member c = n[i];
            byte[] bytes = ByteUtil.merge(c.getAccessFlagsBytes(), c.getNameIndexBytes(),
                    c.getDescriptorIndexBytes(), c.getAttributesCountBytes());
            Attribute[] attributes = c.getAttributes();
            for (Attribute a : attributes) {
                bytes = ByteUtil.merge(bytes, a.getAttributeNameIndexBytes(), a.getAttributeLengthBytes(),
                        a.getInfoBytes());
            }
            f.format("    |%03d| %s%n", i + 1,
                    HexUtil.format(bytes));
        }
    }

    @Override
    public void visitMagic(Formatter f, Node n) {
        f.format("%s: %s --> 咖啡馆宝贝%n", "u4 magic", HexUtil.format(n.getBytes()));
    }

    @Override
    public void visitMinorVersion(Formatter f, Node n) {
        visit("u2 minor_version", f, n.getBytes());
    }

    @Override
    public void visitMajorVersion(Formatter f, Node n) {
        visit("u2 major_version", f, n.getBytes());
    }

    @Override
    public void visitConstantPoolCount(Formatter f, Node n) {
        visit("u2 constant_pool_count", f, n.getBytes());
    }

    @Override
    public void visitConstantPool(Formatter f, Constant[] n) {
        f.format("%s:%n", "cp_info constant_pool[constant_pool_count-1]");
        for (Constant c : n) {
            if (c != null) {
                byte tag = (byte) ByteUtil.toUnsignedInt(c.getTag());
                strategyMap.getOrDefault(tag, (a, b) -> {
                    throw new RuntimeException(String.format("无效常量池类型: %d", tag));
                }).accept(f, c);
            }
        }
    }

    @Override
    public void visitAccessFlags(Formatter f, Node n) {
        byte[] bytes = n.getBytes();
        f.format("%s: %s --> 0B%s%n", "u2 access_flags", HexUtil.format(bytes),
                Integer.toBinaryString(ByteUtil.toUnsignedInt(bytes)));
    }

    @Override
    public void visitThisClass(Formatter f, Node n) {
        visit("u2 this_class", f, n.getBytes(), true);
    }

    @Override
    public void visitSuperClass(Formatter f, Node n) {
        visit("u2 super_class", f, n.getBytes(), true);
    }

    @Override
    public void visitInterfacesCount(Formatter f, Node n) {
        visit("u2 interfaces_count", f, n.getBytes());
    }

    @Override
    public void visitInterfaces(Formatter f, Node[] n) {
        f.format("%s:%n", "u2 interfaces[interfaces_count]");
        for (int i = 0; i < n.length; i++) {
            byte[] bytes = n[i].getBytes();
            f.format("    |%03d| %s --> cp[%03d]%n", i + 1, HexUtil.format(bytes), ByteUtil.toUnsignedInt(bytes));
        }
    }

    @Override
    public void visitFieldsCount(Formatter f, Node n) {
        visit("u2 fields_count", f, n.getBytes());
    }

    @Override
    public void visitFields(Formatter f, Member[] n) {
        visitMember("field_info fields[fields_count]", f, n);
    }

    @Override
    public void visitMethodsCount(Formatter f, Node n) {
        visit("u2 methods_count", f, n.getBytes());
    }

    @Override
    public void visitMethods(Formatter f, Member[] n) {
        visitMember("method_info methods[methods_count]", f, n);
    }

    @Override
    public void visitAttributesCount(Formatter f, Node n) {
        visit("u2 attributes_count", f, n.getBytes());
    }

    @Override
    public void visitAttributes(Formatter f, Attribute[] n) {
        f.format("%s:%n", "attribute_info attributes[attributes_count]");
        for (int i = 0; i < n.length; i++) {
            Attribute a = n[i];
            f.format("    |%03d| %s%n", i + 1,
                    HexUtil.format(ByteUtil.merge(a.getAttributeNameIndexBytes(), a.getAttributeLengthBytes(),
                            a.getInfoBytes())));
        }
    }

}