package zlhywlf.javaasm.classfile;

import java.util.Formatter;
import java.util.LinkedHashMap;
import java.util.Map;

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

    private Map<Integer, Node> nodeMap;

    public ClassFile(Map<Integer, Node> nodeMap) {
        this.nodeMap = nodeMap;
    }

    @Override
    public void accept(Visitor v) {
        nodeMap.forEach((id, node) -> node.accept(v));
    }

    public static String parse(byte[] bytes, Visitor v) {
        StringBuilder sb = new StringBuilder();
        Formatter fm = new Formatter(sb);
        Map<Integer, Node> nodeMap = new LinkedHashMap<>(4);
        ClassFileReader reader = new ClassFileReader(bytes);
        nodeMap.put(1, new Magic(reader, fm));
        nodeMap.put(2, new MinorVersion(reader, fm));
        nodeMap.put(3, new MajorVersion(reader, fm));
        nodeMap.put(4, new ConstantPoolCount(reader, fm));
        nodeMap.put(5, new ConstantPool(reader, fm));
        fm.format("ClassFile {%n");
        new ClassFile(nodeMap).accept(v);
        fm.format("}");
        fm.close();
        return sb.toString();
    }

    public static String parse(byte[] bytes) {
        return HexUtil.format(bytes, HexUtil.HexFormat.FORMAT_FF_SPACE_FF_32);
    }

}
