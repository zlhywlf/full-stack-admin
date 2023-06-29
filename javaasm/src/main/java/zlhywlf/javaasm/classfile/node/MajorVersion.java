package zlhywlf.javaasm.classfile.node;

import java.util.Formatter;

import zlhywlf.javaasm.classfile.ClassFileReader;
import zlhywlf.javaasm.classfile.visitor.Visitor;
import zlhywlf.javaasm.cnst.ClassFileConst;
import zlhywlf.javaasm.util.ByteUtil;

/**
 * u2 major_version;
 * 
 * Java SE | Released | Major
 * 1.0.2 | May-96 | 45
 * 1.1 | Feb-97 | 45
 * 1.2 | Dec-98 | 46
 * 1.3 | May-00 | 47
 * 1.4 | Feb-02 | 48
 * 5 | Sep-04 | 49
 * 6 | Dec-06 | 50
 * 7 | Jul-11 | 51
 * 8 | Mar-14 | 52
 * 9 | Sep-17 | 53
 * 10 | Mar-18 | 54
 * 11 | Sep-18 | 55
 * 12 | Mar-19 | 56
 * 13 | Sep-19 | 57
 * 14 | Mar-20 | 58
 * 15 | Sep-20 | 59
 * 16 | Mar-21 | 60
 * 17 | Sep-21 | 61
 * 
 * @author zlhywlf
 */
public final class MajorVersion extends Node {

    public MajorVersion(ClassFileReader reader, Formatter fm) {
        super(3, reader, fm);
    }

    @Override
    public void accept(Visitor v) {
        v.visitMajorVersion(this.init());
    }

    @Override
    protected Node init() {
        bytes = reader.next(ClassFileConst.MAJOR_VERSION_SIZE);
        value = String.valueOf(ByteUtil.toInt(bytes));
        return this;
    }

}
