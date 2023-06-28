package zlhywlf.javaasm.classfile.node;

import java.util.Formatter;

import zlhywlf.javaasm.classfile.ClassFileReader;
import zlhywlf.javaasm.classfile.visitor.Visitor;
import zlhywlf.javaasm.cnst.ClassFileConst;
import zlhywlf.javaasm.util.ByteUtil;

/**
 * cp_info constant_pool[constant_pool_count-1];
 * 
 * @see https://docs.oracle.com/javase/specs/jvms/se17/html/jvms-4.html#jvms-4.4
 * @author zlhywlf
 */
public class ConstantPool extends Node {

    public ConstantPool(ClassFileReader reader, Formatter fm) {
        super(reader, fm);
    }

    @Override
    public void accept(Visitor v) {
        v.visitConstantPool(this);
    }

    @Override
    public byte[] init() {
        int count = ByteUtil.toInt(reader.loc(ClassFileConst.MAGIC_SIZE
                + ClassFileConst.MINOR_VERSION_SIZE + ClassFileConst.MAJOR_VERSION_SIZE,
                ClassFileConst.CONSTANT_POOL_COUNT_SIZE));
        for (int i = 0; i < count; i++) {
            byte tag = reader.peek();
            switch (tag) {
                case ClassFileConst.CONSTANT_CLASS:
                    // TODO
                    break;
                case ClassFileConst.CONSTANT_FIELDREF:
                    break;
                case ClassFileConst.CONSTANT_METHODREF:
                    break;
                case ClassFileConst.CONSTANT_INTERFACEMETHODREF:
                    break;
                case ClassFileConst.CONSTANT_String:
                    break;
                case ClassFileConst.CONSTANT_INTEGER:
                    break;
                case ClassFileConst.CONSTANT_FLOAT:
                    break;
                case ClassFileConst.CONSTANT_LONG:
                    break;
                case ClassFileConst.CONSTANT_DOUBLE:
                    break;
                case ClassFileConst.CONSTANT_NAMEANDTYPE:
                    break;
                case ClassFileConst.CONSTANT_UTF8:
                    break;
                case ClassFileConst.CONSTANT_METHODHANDLE:
                    break;
                case ClassFileConst.CONSTANT_METHODTYPE:
                    break;
                case ClassFileConst.CONSTANT_DYNAMIC:
                    break;
                case ClassFileConst.CONSTANT_INVOKEDYNAMIC:
                    break;
                case ClassFileConst.CONSTANT_MODULE:
                    break;
                case ClassFileConst.CONSTANT_PACKAGE:
                    break;
                default:
                    throw new RuntimeException("无效常量池类型" + tag);
            }
        }
        return new byte[0];
    }

}
