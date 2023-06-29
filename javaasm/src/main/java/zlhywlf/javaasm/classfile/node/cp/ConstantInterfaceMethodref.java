package zlhywlf.javaasm.classfile.node.cp;

import java.util.Formatter;

import zlhywlf.javaasm.classfile.ClassFileReader;
import zlhywlf.javaasm.classfile.visitor.Visitor;

/**
 * CONSTANT_InterfaceMethodref_info {
 * u1 tag;
 * u2 class_index;
 * u2 name_and_type_index;
 * }
 * 
 * @author zlhywlf
 */
public class ConstantInterfaceMethodref extends ConstantMethodref {

    public ConstantInterfaceMethodref(int id, ClassFileReader reader, Formatter fm) {
        super(id, reader, fm);
    }

    @Override
    public void accept(Visitor v) {
        v.visitConstantInterfaceMethodref(this);
    }

}
