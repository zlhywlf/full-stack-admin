package zlhywlf.javaasm.classfile.node.cp;

import java.util.Formatter;

import zlhywlf.javaasm.classfile.ClassFileReader;
import zlhywlf.javaasm.classfile.node.Node;
import zlhywlf.javaasm.classfile.visitor.Visitor;
import zlhywlf.javaasm.util.ByteUtil;

/**
 * CONSTANT_InvokeDynamic_info {
 * u1 tag;
 * u2 bootstrap_method_attr_index;
 * u2 name_and_type_index;
 * }
 * 
 * @author zlhywlf
 */
public class ConstantInvokeDynamic extends Constant {

    public ConstantInvokeDynamic(int id, ClassFileReader reader, Formatter fm) {
        super(id, reader, fm);
    }

    @Override
    public void accept(Visitor v) {
        v.visitConstantInvokeDynamic(this);
    }

    @Override
    protected Node init() {
        byte[] tagBytes = reader.next(1);
        byte[] bootstrapMethodAttrIndexBytes = reader.next(2);
        byte[] nameAndTypeIndexBytes = reader.next(2);
        int bootstrapMethodAttrIndex = ByteUtil.toInt(bootstrapMethodAttrIndexBytes);
        int nameAndTypeIndex = ByteUtil.toInt(nameAndTypeIndexBytes);
        value = String.format("#%d:#%d", bootstrapMethodAttrIndex, nameAndTypeIndex);
        bytes = ByteUtil.merge(tagBytes, bootstrapMethodAttrIndexBytes, nameAndTypeIndexBytes);
        return this;
    }

}
