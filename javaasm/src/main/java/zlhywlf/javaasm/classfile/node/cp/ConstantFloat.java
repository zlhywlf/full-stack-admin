package zlhywlf.javaasm.classfile.node.cp;

import java.util.Formatter;

import zlhywlf.javaasm.classfile.ClassFileReader;
import zlhywlf.javaasm.classfile.node.Node;
import zlhywlf.javaasm.classfile.visitor.Visitor;
import zlhywlf.javaasm.util.ByteUtil;

/**
 * CONSTANT_Float_info {
 * u1 tag;
 * u4 bytes;
 * }
 * 
 * @author zlhywlf
 */
public class ConstantFloat extends Constant {

    public ConstantFloat(int id, ClassFileReader reader, Formatter fm) {
        super(id, reader, fm);
    }

    @Override
    public void accept(Visitor v) {
        v.visitConstantFloat(this);
    }

    @Override
    protected Node init() {
        byte[] tagBytes = reader.next(1);
        byte[] valueBytes = reader.next(4);
        value = String.valueOf(ByteUtil.toFloat(valueBytes));
        bytes = ByteUtil.merge(tagBytes, valueBytes);
        return this;
    }

}
