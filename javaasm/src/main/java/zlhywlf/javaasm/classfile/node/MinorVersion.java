package zlhywlf.javaasm.classfile.node;

import java.util.Formatter;

import zlhywlf.javaasm.classfile.ClassFileReader;
import zlhywlf.javaasm.classfile.visitor.Visitor;
import zlhywlf.javaasm.cnst.ClassFileConst;
import zlhywlf.javaasm.util.ByteUtil;

/**
 * u2 minor_version;
 * 
 * @author zlhywlf
 */
public final class MinorVersion extends Node {

    public MinorVersion(ClassFileReader reader, Formatter fm) {
        super(2, reader, fm);
    }

    @Override
    public void accept(Visitor v) {
        v.visitMinorVersion(this.init());
    }

    @Override
    protected Node init() {
        bytes = reader.next(ClassFileConst.MINOR_VERSION_SIZE);
        value = String.valueOf(ByteUtil.toInt(bytes));
        return this;
    }

}
