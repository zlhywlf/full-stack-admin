package zlhywlf.javaasm.classfile.node;

import java.util.Formatter;

import zlhywlf.javaasm.classfile.ClassFileReader;
import zlhywlf.javaasm.classfile.visitor.Visitor;
import zlhywlf.javaasm.util.ByteUtil;

/**
 * u2 access_flags;
 * 
 * Flag Name | Value | Interpretation
 * ACC_PUBLIC | 0x0001 | Declared public; may be accessed from outside its
 * package.
 * ACC_FINAL | 0x0010 | Declared final; no subclasses allowed.
 * ACC_SUPER | 0x0020 | Treat superclass methods specially when invoked by
 * the invokespecial instruction.
 * ACC_INTERFACE | 0x0200 | Is an interface, not a class.
 * ACC_ABSTRACT | 0x0400 | Declared abstract; must not be instantiated.
 * ACC_SYNTHETIC | 0x1000 | Declared synthetic; not present in the source code.
 * ACC_ANNOTATION | 0x2000 | Declared as an annotation interface.
 * ACC_ENUM | 0x4000 | Declared as an enum class.
 * ACC_MODULE | 0x8000 | Is a module, not a class or interface.
 * 
 * @author zlhywlf
 */
public class AccessFlags extends Node {

    public AccessFlags(ClassFileReader reader, Formatter fm) {
        super(6, reader, fm);
    }

    @Override
    public void accept(Visitor v) {
        v.visitAccessFlags(this.init());
    }

    @Override
    protected Node init() {
        bytes = reader.next(2);
        value = String.valueOf(ByteUtil.toInt(bytes));
        return this;
    }

}
