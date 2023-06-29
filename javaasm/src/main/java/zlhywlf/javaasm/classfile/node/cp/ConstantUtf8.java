package zlhywlf.javaasm.classfile.node.cp;

import java.util.Formatter;

import zlhywlf.javaasm.classfile.ClassFileReader;
import zlhywlf.javaasm.classfile.node.Node;
import zlhywlf.javaasm.classfile.visitor.Visitor;
import zlhywlf.javaasm.util.ByteUtil;

/**
 * CONSTANT_Utf8_info {
 * u1 tag;
 * u2 length;
 * u1 bytes[length];
 * }
 * 
 * @author zlhywlf
 */
public class ConstantUtf8 extends Constant {

    public ConstantUtf8(int id, ClassFileReader reader, Formatter fm) {
        super(id, reader, fm);
    }

    @Override
    public void accept(Visitor v) {
        v.visitConstantUtf8(this);
    }

    @Override
    protected Node init() {
        byte[] tagBytes = reader.next(1);
        byte[] lengthBytes = reader.next(2);
        int length = ByteUtil.toInt(lengthBytes);
        byte[] utf8Bytes = reader.next(length);
        value = ByteUtil.toUtf8(utf8Bytes);
        bytes = ByteUtil.merge(tagBytes, lengthBytes, utf8Bytes);
        return this;
    }

}
