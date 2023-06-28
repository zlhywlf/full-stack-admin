package zlhywlf.javaasm.classfile.node;

import java.util.Formatter;

import zlhywlf.javaasm.classfile.ClassFileReader;
import zlhywlf.javaasm.classfile.visitor.Visitor;
import zlhywlf.javaasm.cnst.ClassFileConst;

/**
 * u2 major_version;
 * 
 * @author zlhywlf
 */
public final class MajorVersion extends Node {

    public MajorVersion(ClassFileReader reader, Formatter fm) {
        super(reader, fm);
    }

    @Override
    public void accept(Visitor v) {
        v.visitMajorVersion(this);
    }

    @Override
    public byte[] init() {
        return reader.next(ClassFileConst.MAJOR_VERSION_SIZE);
    }

}
