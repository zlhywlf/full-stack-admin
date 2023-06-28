package zlhywlf.javaasm.classfile;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Formatter;
import java.util.List;

import zlhywlf.javaasm.classfile.node.ConstantPool;
import zlhywlf.javaasm.classfile.node.ConstantPoolCount;
import zlhywlf.javaasm.classfile.node.Magic;
import zlhywlf.javaasm.classfile.node.MajorVersion;
import zlhywlf.javaasm.classfile.node.MinorVersion;
import zlhywlf.javaasm.classfile.node.Node;
import zlhywlf.javaasm.classfile.visitor.Element;
import zlhywlf.javaasm.classfile.visitor.Visitor;
import zlhywlf.javaasm.util.HexUtil;

/**
 * ClassFile {
 * u4 magic;
 * u2 minor_version;
 * u2 major_version;
 * u2 constant_pool_count;
 * cp_info constant_pool[constant_pool_count-1];
 * u2 access_flags;
 * u2 this_class;
 * u2 super_class;
 * u2 interfaces_count;
 * u2 interfaces[interfaces_count];
 * u2 fields_count;
 * field_info fields[fields_count];
 * u2 methods_count;
 * method_info methods[methods_count];
 * u2 attributes_count;
 * attribute_info attributes[attributes_count];
 * }
 * 
 * @see https://docs.oracle.com/javase/specs/jvms/se17/html/jvms-4.html
 * @author zlhywlf
 */
public final class ClassFile implements Element {

    private List<Node> nodeList;

    public ClassFile(List<Node> nodeList) {
        this.nodeList = nodeList;
    }

    @Override
    public void accept(Visitor v) {
        nodeList.stream().sorted(Comparator.comparing(Node::getId)).forEach(node -> node.accept(v));
    }

    public static String parse(byte[] bytes, Visitor v) {
        StringBuilder sb = new StringBuilder();
        Formatter fm = new Formatter(sb);
        List<Node> nodeList = new ArrayList<>(16);
        ClassFileReader reader = new ClassFileReader(bytes);
        nodeList.add(new ConstantPool(reader, fm));
        nodeList.add(new ConstantPoolCount(reader, fm));
        nodeList.add(new MinorVersion(reader, fm));
        nodeList.add(new Magic(reader, fm));
        nodeList.add(new MajorVersion(reader, fm));
        fm.format("ClassFile {%n");
        new ClassFile(nodeList).accept(v);
        fm.format("}");
        fm.close();
        return sb.toString();
    }

    public static String parse(byte[] bytes) {
        return HexUtil.format(bytes, HexUtil.HexFormat.FORMAT_FF_SPACE_FF_32);
    }

}
