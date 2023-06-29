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
public class InterfacesCount extends Node {

    public InterfacesCount(ClassFileReader reader, Formatter fm) {
        super((byte) 9, reader, fm);
    }

    @Override
    public void accept(Visitor v) {
        v.visitInterfacesCount(this.init());
    }

    @Override
    protected Node init() {
        bytes = reader.next(2);
        value = String.valueOf(ByteUtil.toInt(bytes));
        return this;
    }

}
