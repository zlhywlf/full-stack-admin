package zlhywlf.javaasm.classfile.node.cp;

import java.util.Formatter;

import zlhywlf.javaasm.classfile.ClassFileReader;
import zlhywlf.javaasm.classfile.node.Node;
import zlhywlf.javaasm.classfile.visitor.Visitor;
import zlhywlf.javaasm.util.ByteUtil;

/**
 * CONSTANT_MethodType_info {
 * u1 tag;
 * u2 descriptor_index;
 * }
 * 
 * @author zlhywlf
 */
public class ConstantMethodType extends Constant {

    public ConstantMethodType(int id, ClassFileReader reader, Formatter fm) {
        super(id, reader, fm);
    }

    @Override
    public void accept(Visitor v) {
        v.visitConstantMethodType(this);
    }

    @Override
    protected Node init() {
        byte[] tagBytes = reader.next(1);
        byte[] descriptorIndexBytes = reader.next(2);
        value = String.format("#%d", ByteUtil.toInt(descriptorIndexBytes));
        bytes = ByteUtil.merge(tagBytes, descriptorIndexBytes);
        return this;
    }

}
