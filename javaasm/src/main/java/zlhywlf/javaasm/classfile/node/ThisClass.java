package zlhywlf.javaasm.classfile.node;

import java.util.Formatter;

import zlhywlf.javaasm.classfile.ClassFileReader;
import zlhywlf.javaasm.classfile.visitor.Visitor;
import zlhywlf.javaasm.util.ByteUtil;

/**
 * u2 this_class;
 * 
 * @author zlhywlf
 */
public class ThisClass extends Node {

    public ThisClass(ClassFileReader reader, Formatter fm) {
        super((byte) 7, reader, fm);
    }

    @Override
    public void accept(Visitor v) {
        v.visitThisClass(this.init());
    }

    @Override
    protected Node init() {
        bytes = reader.next(2);
        value = String.format("#%d", ByteUtil.toInt(bytes));
        return this;
    }

}
