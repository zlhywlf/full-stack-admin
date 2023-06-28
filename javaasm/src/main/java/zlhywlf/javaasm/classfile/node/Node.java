package zlhywlf.javaasm.classfile.node;

import java.util.Formatter;

import lombok.Getter;
import lombok.Setter;
import zlhywlf.javaasm.classfile.ClassFileReader;
import zlhywlf.javaasm.classfile.visitor.Element;
import zlhywlf.javaasm.util.HexUtil;

@Getter
@Setter
public abstract class Node implements Element {

    protected byte[] bytes;
    protected String value;
    protected Formatter fm;
    protected ClassFileReader reader;

    public Node(ClassFileReader reader, Formatter fm) {
        this.fm = fm;
        this.reader = reader;
        this.bytes = init();
    }

    public String toHex() {
        return HexUtil.format(this.bytes, HexUtil.HexFormat.FORMAT_FF_FF);
    }

    public abstract byte[] init();

}
