package zlhywlf.javaasm.classfile.node.cp;

import java.util.Formatter;

import zlhywlf.javaasm.classfile.ClassFileReader;
import zlhywlf.javaasm.classfile.node.Node;
import zlhywlf.javaasm.classfile.visitor.Visitor;
import zlhywlf.javaasm.util.ByteUtil;

/**
 * CONSTANT_String_info {
 * u1 tag;
 * u2 string_index;
 * }
 * 
 * @author zlhywlf
 */
public class ConstantString extends Constant {

    public ConstantString(int id, ClassFileReader reader, Formatter fm) {
        super(id, reader, fm);
    }

    @Override
    public void accept(Visitor v) {
        v.visitConstantString(this);
    }

    @Override
    protected Node init() {
        byte[] tagBytes = reader.next(1);
        byte[] stringIndexBytes = reader.next(2);
        value = String.format("#%d", ByteUtil.toInt(stringIndexBytes));
        bytes = ByteUtil.merge(tagBytes, stringIndexBytes);
        return this;
    }

}
