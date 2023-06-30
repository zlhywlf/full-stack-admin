package zlhywlf.javaasm.classfile.node;

import java.util.Formatter;

import zlhywlf.javaasm.classfile.ClassFileReader;
import zlhywlf.javaasm.classfile.visitor.Visitor;
import zlhywlf.javaasm.util.ByteUtil;

/**
 * u2 super_class;
 * 
 * @author zlhywlf
 */
public class SuperClass extends Node {

    public SuperClass(ClassFileReader reader, Formatter fm) {
        super(8, reader, fm);
    }

    @Override
    public void accept(Visitor v) {
        v.visitSuperClass(init());
    }

    @Override
    protected Node init() {
        bytes = reader.next(2);
        value = String.format("#%d", ByteUtil.toInt(bytes));
        return this;
    }

}
