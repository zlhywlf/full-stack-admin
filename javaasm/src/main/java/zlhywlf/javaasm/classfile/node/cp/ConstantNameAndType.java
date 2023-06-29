package zlhywlf.javaasm.classfile.node.cp;

import java.util.Formatter;

import zlhywlf.javaasm.classfile.ClassFileReader;
import zlhywlf.javaasm.classfile.node.Node;
import zlhywlf.javaasm.classfile.visitor.Visitor;
import zlhywlf.javaasm.util.ByteUtil;

/**
 * CONSTANT_NameAndType_info {
 * u1 tag;
 * u2 name_index;
 * u2 descriptor_index;
 * }
 * 
 * @author zlhywlf
 */
public class ConstantNameAndType extends Constant {

    public ConstantNameAndType(int id, ClassFileReader reader, Formatter fm) {
        super(id, reader, fm);
    }

    @Override
    public void accept(Visitor v) {
        v.visitConstantNameAndType(this);
    }

    @Override
    protected Node init() {
        byte[] tagBytes = reader.next(1);
        byte[] nameIndexBytes = reader.next(2);
        byte[] descriptorIndexBytes = reader.next(2);
        int nameIndex = ByteUtil.toInt(nameIndexBytes);
        int descriptorIndex = ByteUtil.toInt(descriptorIndexBytes);
        value = String.format("#%d:#%d", nameIndex, descriptorIndex);
        bytes = ByteUtil.merge(tagBytes, nameIndexBytes, descriptorIndexBytes);
        return this;
    }

}
