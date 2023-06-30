package zlhywlf.javaasm.classfile.node;

import java.util.Formatter;

import zlhywlf.javaasm.classfile.ClassFileReader;
import zlhywlf.javaasm.classfile.visitor.Visitor;
import zlhywlf.javaasm.util.ByteUtil;

/**
 * u2 methods_count;
 * 
 * @author zlhywlf
 */
public class MethodsCount extends Node {

    public MethodsCount(ClassFileReader reader, Formatter fm) {
        super(13, reader, fm);
    }

    @Override
    public void accept(Visitor v) {
        v.visitMethodsCount(this.init());
    }

    @Override
    protected Node init() {
        bytes = reader.next(2);
        value = String.valueOf(ByteUtil.toInt(bytes));
        return this;
    }

}
