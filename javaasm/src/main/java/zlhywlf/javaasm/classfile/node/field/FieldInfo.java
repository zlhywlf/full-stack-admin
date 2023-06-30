package zlhywlf.javaasm.classfile.node.field;

import java.util.Formatter;

import zlhywlf.javaasm.classfile.ClassFileReader;
import zlhywlf.javaasm.classfile.node.Node;
import zlhywlf.javaasm.classfile.node.attr.AttributeInfo;
import zlhywlf.javaasm.classfile.visitor.Visitor;
import zlhywlf.javaasm.util.ByteUtil;

/**
 * field_info {
 * u2 access_flags;
 * u2 name_index;
 * u2 descriptor_index;
 * u2 attributes_count;
 * attribute_info attributes[attributes_count];
 * }
 * 
 * Flag Name | Value | Interpretation
 * ACC_PUBLIC | 0x0001 | Declared public; may be accessed from outside its
 * package.
 * ACC_PRIVATE | 0x0002 | Declared private; accessible only within the defining
 * class and other classes belonging to the same nest (§5.4.4).
 * ACC_PROTECTED | 0x0004 | Declared protected; may be accessed within
 * subclasses.
 * ACC_STATIC | 0x0008 | Declared static.
 * ACC_FINAL | 0x0010 | Declared final; never directly assigned to after object
 * construction (JLS §17.5).
 * ACC_VOLATILE | 0x0040 | Declared volatile; cannot be cached.
 * ACC_TRANSIENT | 0x0080 | Declared transient; not written or read by a
 * persistent object manager.
 * ACC_SYNTHETIC | 0x1000 | Declared synthetic; not present in the source code.
 * ACC_ENUM | 0x4000 | Declared as an element of an enum class.
 * 
 * 
 * @author zlhywlf
 */
public class FieldInfo extends Node {

    private AttributeInfo[] attributeInfos;

    public FieldInfo(int id, ClassFileReader reader, Formatter fm) {
        super(id, reader, fm);
        init();
    }

    @Override
    public void accept(Visitor v) {
        v.visitFieldInfo(this);
        for (AttributeInfo a : attributeInfos) {
            a.accept(v);
        }
    }

    @Override
    protected Node init() {
        byte[] accessFlagsBytes = reader.next(2);
        byte[] nameIndexBytes = reader.next(2);
        byte[] descriptorIndexBytes = reader.next(2);
        byte[] attributesCountBytes = reader.next(2);
        bytes = ByteUtil.merge(accessFlagsBytes, nameIndexBytes, descriptorIndexBytes, attributesCountBytes);
        int attributesCount = ByteUtil.toInt(attributesCountBytes);
        attributeInfos = new AttributeInfo[attributesCount];
        for (int i = 0; i < attributesCount; i++) {
            AttributeInfo a = new AttributeInfo(i + 1, reader, fm);
            attributeInfos[i] = a;
            bytes = ByteUtil.merge(bytes, a.getBytes());
        }
        value = String.format("%d,#%d,#%d,%d", ByteUtil.toInt(accessFlagsBytes), ByteUtil.toInt(nameIndexBytes),
                ByteUtil.toInt(descriptorIndexBytes), attributesCount);
        return this;
    }

}
