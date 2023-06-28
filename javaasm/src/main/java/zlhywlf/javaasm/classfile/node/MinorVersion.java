package zlhywlf.javaasm.classfile.node;

import java.util.Formatter;

import zlhywlf.javaasm.classfile.ClassFileReader;
import zlhywlf.javaasm.classfile.visitor.Visitor;
import zlhywlf.javaasm.cnst.ClassFileConst;

/**
 * u2 minor_version;
 * 
 * @author zlhywlf
 */
public final class MinorVersion extends Node {

    public MinorVersion(ClassFileReader reader, Formatter fm) {
        super(reader, fm);
    }

    @Override
    public void accept(Visitor v) {
        v.visitMinorVersion(this);
    }

    @Override
    public byte[] init() {
        return reader.next(ClassFileConst.MINOR_VERSION_SIZE);
    }

}
