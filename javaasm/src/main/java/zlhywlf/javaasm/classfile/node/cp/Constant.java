package zlhywlf.javaasm.classfile.node.cp;

import java.util.Formatter;

import zlhywlf.javaasm.classfile.ClassFileReader;
import zlhywlf.javaasm.classfile.node.Node;

public abstract class Constant extends Node {

    public Constant(int id, ClassFileReader reader, Formatter fm) {
        super(id, reader, fm);
        // 必须在构造时初始化
        init();
    }

}
