package zlhywlf.javaasm.classfile.node.attr;

import java.util.Formatter;

import zlhywlf.javaasm.classfile.ClassFileReader;
import zlhywlf.javaasm.classfile.node.Node;
import zlhywlf.javaasm.classfile.visitor.Visitor;
import zlhywlf.javaasm.util.ByteUtil;

/**
 * attribute_info {
 * u2 attribute_name_index;
 * u4 attribute_length;
 * u1 info[attribute_length];
 * }
 * 
 * @author zlhywlf
 */
public class AttributeInfo extends Node {

    public AttributeInfo(int id, ClassFileReader reader, Formatter fm) {
        super(id, reader, fm);
        init();
    }

    @Override
    public void accept(Visitor v) {
        v.visitAttributeInfo(this);
    }

    @Override
    protected Node init() {
        byte[] attributeNameIndexBytes = reader.next(2);
        byte[] attributeLengthBytes = reader.next(4);
        int attributeLength = ByteUtil.toInt(attributeLengthBytes);
        byte[] infoBytes = reader.next(attributeLength);
        bytes = ByteUtil.merge(attributeNameIndexBytes, attributeLengthBytes, infoBytes);
        value = String.format("#%d,%d", ByteUtil.toInt(attributeNameIndexBytes), attributeLength);
        return this;
    }

}
