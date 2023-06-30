package zlhywlf.javaasm.classfile.node;

import java.util.Formatter;

import zlhywlf.javaasm.classfile.ClassFileReader;
import zlhywlf.javaasm.classfile.visitor.Visitor;
import zlhywlf.javaasm.util.ByteUtil;

/**
 * u2 interfaces_count;
 * 
 * @author zlhywlf
 */
public class FieldsCount extends Node {

    public FieldsCount(ClassFileReader reader, Formatter fm) {
        super(11, reader, fm);
    }

    @Override
    public void accept(Visitor v) {
        v.visitFieldsCount(this.init());
    }

    @Override
    protected Node init() {
        bytes = reader.next(2);
        value = String.valueOf(ByteUtil.toInt(bytes));
        return this;
    }

}
