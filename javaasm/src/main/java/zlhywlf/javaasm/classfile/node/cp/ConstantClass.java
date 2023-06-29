package zlhywlf.javaasm.classfile.node.cp;

import java.util.Formatter;

import zlhywlf.javaasm.classfile.ClassFileReader;
import zlhywlf.javaasm.classfile.node.Node;
import zlhywlf.javaasm.classfile.visitor.Visitor;
import zlhywlf.javaasm.util.ByteUtil;

/**
 * CONSTANT_Class_info {
 * u1 tag;
 * u2 name_index;
 * }
 * 
 * @author zlhywlf
 */
public class ConstantClass extends Constant {

    public ConstantClass(int id, ClassFileReader reader, Formatter fm) {
        super(id, reader, fm);
    }

    @Override
    public void accept(Visitor v) {
        v.visitConstantClass(this);
    }

    @Override
    protected Node init() {
        byte[] tagBytes = reader.next(1);
        byte[] nameIndexBytes = reader.next(2);
        value = String.format("#%d", ByteUtil.toInt(nameIndexBytes));
        bytes = ByteUtil.merge(tagBytes, nameIndexBytes);
        return this;
    }

}