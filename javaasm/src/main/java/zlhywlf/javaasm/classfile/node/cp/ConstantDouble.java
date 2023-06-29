package zlhywlf.javaasm.classfile.node.cp;

import java.util.Formatter;

import zlhywlf.javaasm.classfile.ClassFileReader;
import zlhywlf.javaasm.classfile.node.Node;
import zlhywlf.javaasm.classfile.visitor.Visitor;
import zlhywlf.javaasm.util.ByteUtil;

/**
 * CONSTANT_Double_info {
 * u1 tag;
 * u4 high_bytes;
 * u4 low_bytes;
 * }
 * 
 * @author zlhywlf
 */
public class ConstantDouble extends Constant {

    public ConstantDouble(int id, ClassFileReader reader, Formatter fm) {
        super(id, reader, fm);
    }

    @Override
    public void accept(Visitor v) {
        v.visitConstantDouble(this);
    }

    @Override
    protected Node init() {
        byte[] tagBytes = reader.next(1);
        byte[] highBytes = reader.next(4);
        byte[] lowBytes = reader.next(4);
        value = String.valueOf(ByteUtil.toDouble(ByteUtil.merge(highBytes, lowBytes)));
        bytes = ByteUtil.merge(tagBytes, highBytes, lowBytes);
        return this;
    }

}
