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
        super(4, reader, fm);
    }

    @Override
    public void accept(Visitor v) {
        v.visitConstantPoolCount(this.init());
    }

    @Override
    protected Node init() {
        bytes = reader.next(ClassFileConst.CONSTANT_POOL_COUNT_SIZE);
        return this;
    }

}
