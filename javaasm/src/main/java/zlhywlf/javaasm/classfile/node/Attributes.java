package zlhywlf.javaasm.classfile.node;

import java.util.Formatter;

import zlhywlf.javaasm.classfile.ClassFileReader;
import zlhywlf.javaasm.classfile.node.attr.AttributeInfo;
import zlhywlf.javaasm.classfile.visitor.Visitor;
import zlhywlf.javaasm.util.ByteUtil;

/**
 * attribute_info attributes[attributes_count];
 * 
 * @author zlhywlf
 */
public class Attributes extends Node {

    private AttributeInfo[] attributeInfos;

    public Attributes(ClassFileReader reader, Formatter fm) {
        super(16, reader, fm);
    }

    @Override
    public void accept(Visitor v) {
        v.visitAttributes(this.init());
        for (AttributeInfo a : attributeInfos) {
            a.accept(v);
        }
    }

    @Override
    protected Node init() {
        int count = ByteUtil.toInt(reader.previous(2, 2));
        attributeInfos = new AttributeInfo[count];
        if (count > 0) {
            for (int i = 0; i < count; i++) {
                attributeInfos[i] = new AttributeInfo(i + 1, reader, fm);
            }
        }
        return this;
    }

}
