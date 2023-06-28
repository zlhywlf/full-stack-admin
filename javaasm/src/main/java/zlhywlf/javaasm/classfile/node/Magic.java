package zlhywlf.javaasm.classfile.node;

import java.util.Formatter;

import zlhywlf.javaasm.classfile.ClassFileReader;
import zlhywlf.javaasm.classfile.visitor.Visitor;
import zlhywlf.javaasm.cnst.ClassFileConst;
import zlhywlf.javaasm.util.ByteUtil;

/**
 * u4 magic;
 * 
 * @author zlhywlf
 */
public final class Magic extends Node {

    public Magic(ClassFileReader reader, Formatter fm) {
        super(1, reader, fm);
    }

    @Override
    public void accept(Visitor v) {
        v.visitMagic(this.init());
    }

    @Override
    protected Node init() {
        bytes = reader.next(ClassFileConst.MAGIC_SIZE);
        int magic = ByteUtil.toInt(bytes);
        if (magic != ClassFileConst.MAGIC_VALUE) {
            throw new RuntimeException("不是标准的 .class 文件");
        }
        return this;
    }

}
