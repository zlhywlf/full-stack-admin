package zlhywlf.javaasm.classfile.node;

import java.util.Formatter;

import zlhywlf.javaasm.classfile.ClassFileReader;
import zlhywlf.javaasm.classfile.visitor.Visitor;
import zlhywlf.javaasm.util.ByteUtil;

/**
 * u2 interfaces[interfaces_count];
 * 
 * @author zlhywlf
 */
public class Interfaces extends Node {

    public Interfaces(ClassFileReader reader, Formatter fm) {
        super(10, reader, fm);
    }

    @Override
    public void accept(Visitor v) {
        v.visitInterfaces(this.init());
    }

    @Override
    protected Node init() {
        int count = ByteUtil.toInt(reader.previous(2, 2));
        if (count > 0) {
            StringBuilder sb = new StringBuilder();
            sb.append("[");
            for (int i = 0; i < count; i++) {
                byte[] inter = reader.next(2);
                bytes = ByteUtil.merge(bytes, inter);
                sb.append(String.format("#%d", ByteUtil.toInt(inter))).append(i == count - 1 ? "]" : ", ");
            }
            value = sb.toString();
        }
        return this;
    }

}
