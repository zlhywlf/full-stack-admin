package zlhywlf.javaasm.classfile.node.cp;

import java.util.Formatter;

import zlhywlf.javaasm.classfile.ClassFileReader;
import zlhywlf.javaasm.classfile.node.Node;
import zlhywlf.javaasm.classfile.visitor.Visitor;
import zlhywlf.javaasm.util.ByteUtil;

/**
 * CONSTANT_MethodHandle_info {
 * u1 tag;
 * u1 reference_kind;
 * u2 reference_index;
 * }
 * 
 * @author zlhywlf
 */
public class ConstantMethodHandle extends Constant {

    public ConstantMethodHandle(int id, ClassFileReader reader, Formatter fm) {
        super(id, reader, fm);
    }

    @Override
    public void accept(Visitor v) {
        v.visitConstantMethodHandle(this);
    }

    @Override
    protected Node init() {
        byte[] tagBytes = reader.next(1);
        byte[] referenceKindBytes = reader.next(1);
        byte[] referenceIndexBytes = reader.next(2);
        value = String.format("%d #%d", ByteUtil.toInt(referenceKindBytes), ByteUtil.toInt(referenceIndexBytes));
        bytes = ByteUtil.merge(tagBytes, referenceKindBytes, referenceIndexBytes);
        return this;
    }

}
