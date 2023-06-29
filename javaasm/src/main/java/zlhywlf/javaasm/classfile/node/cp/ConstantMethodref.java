package zlhywlf.javaasm.classfile.node.cp;

import java.util.Formatter;

import zlhywlf.javaasm.classfile.ClassFileReader;
import zlhywlf.javaasm.classfile.node.Node;
import zlhywlf.javaasm.classfile.visitor.Visitor;
import zlhywlf.javaasm.util.ByteUtil;

/**
 * CONSTANT_Methodref_info {
 * u1 tag;
 * u2 class_index;
 * u2 name_and_type_index;
 * }
 * 
 * @author zlhywlf
 */
public class ConstantMethodref extends Constant {

    public ConstantMethodref(int id, ClassFileReader reader, Formatter fm) {
        super(id, reader, fm);
    }

    @Override
    public void accept(Visitor v) {
        v.visitConstantMethodref(this);
    }

    @Override
    protected Node init() {
        byte[] tagBytes = reader.next(1);
        byte[] classIndexBytes = reader.next(2);
        byte[] nameAndTypeIndexBytes = reader.next(2);
        int classIndex = ByteUtil.toInt(classIndexBytes);
        int nameAndTypeIndex = ByteUtil.toInt(nameAndTypeIndexBytes);
        value = String.format("#%d.#%d", classIndex, nameAndTypeIndex);
        bytes = ByteUtil.merge(tagBytes, classIndexBytes, nameAndTypeIndexBytes);
        return this;
    }

}
