package zlhywlf.javaasm.classfile.node;

import java.util.Formatter;

import zlhywlf.javaasm.classfile.ClassFileReader;
import zlhywlf.javaasm.classfile.visitor.Visitor;
import zlhywlf.javaasm.util.ByteUtil;

/**
 * u2 attributes_count;
 * 
 * @author zlhywlf
 */
public class AttributesCount extends Node {

    public AttributesCount(ClassFileReader reader, Formatter fm) {
        super(15, reader, fm);
    }

    @Override
    public void accept(Visitor v) {
        v.visitAttributesCount(this.init());
    }

    @Override
    protected Node init() {
        bytes = reader.next(2);
        value = String.valueOf(ByteUtil.toInt(bytes));
        return this;
    }

}
