package zlhywlf.javaasm.classfile.node;

import java.util.Formatter;

import zlhywlf.javaasm.classfile.ClassFileReader;
import zlhywlf.javaasm.classfile.visitor.Visitor;
import zlhywlf.javaasm.cnst.ClassFileConst;

/**
 * u2 constant_pool_count;
 * 
 * @author zlhywlf
 */
public final class ConstantPoolCount extends Node {

    public ConstantPoolCount(ClassFileReader reader, Formatter fm) {
        super(reader, fm);
    }

    @Override
    public void accept(Visitor v) {
        v.visitConstantPoolCount(this);
    }

    @Override
    public byte[] init() {
        return reader.next(ClassFileConst.CONSTANT_POOL_COUNT_SIZE);
    }

}
