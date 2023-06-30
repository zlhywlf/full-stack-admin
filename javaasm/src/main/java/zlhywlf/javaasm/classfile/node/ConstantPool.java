package zlhywlf.javaasm.classfile.node;

import java.util.Formatter;

import zlhywlf.javaasm.classfile.ClassFileReader;
import zlhywlf.javaasm.classfile.node.cp.*;
import zlhywlf.javaasm.classfile.visitor.Visitor;
import zlhywlf.javaasm.cnst.ClassFileConst;
import zlhywlf.javaasm.util.ByteUtil;

/**
 * cp_info constant_pool[constant_pool_count-1];
 * 
 * cp_info {
 * u1 tag;
 * u1 info[];
 * }
 * 
 * CONSTANT_Class 7
 * CONSTANT_Fieldref 9
 * CONSTANT_Methodref 10
 * CONSTANT_InterfaceMethodref 11
 * CONSTANT_String 8
 * CONSTANT_Integer 3
 * CONSTANT_Float 4
 * CONSTANT_Long 5
 * CONSTANT_Double 6
 * CONSTANT_NameAndType 12
 * CONSTANT_Utf8 1
 * CONSTANT_MethodHandle 15
 * CONSTANT_MethodType 16
 * CONSTANT_Dynamic 17
 * CONSTANT_InvokeDynamic 18
 * CONSTANT_Module 19
 * CONSTANT_Package 20
 * 
 * 
 * All 8-byte constants take up two entries in the constant_pool table of the
 * class file. If a CONSTANT_Long_info or CONSTANT_Double_info structure is the
 * entry at index n in the constant_pool table, then the next usable entry in
 * the table is located at index n+2. The constant_pool index n+1 must be valid
 * but is considered unusable.
 * In retrospect, making 8-byte constants take two constant pool entries was a
 * poor choice.
 * 
 * @see https://docs.oracle.com/javase/specs/jvms/se17/html/jvms-4.html#jvms-4.4
 * @author zlhywlf
 */
public class ConstantPool extends Node {

    private Constant[] constants;

    public ConstantPool(ClassFileReader reader, Formatter fm) {
        super(5, reader, fm);
    }

    @Override
    public void accept(Visitor v) {
        v.visitConstantPool(this.init());
        for (Node constant : constants) {
            if (constant != null) {
                constant.accept(v);
            }
        }
    }

    @Override
    protected Node init() {
        int count = ByteUtil.toInt(reader.previous(2, 2));
        constants = new Constant[count];
        for (int i = 1; i < count; i++) {
            byte tag = reader.peek();
            switch (tag) {
                case ClassFileConst.CONSTANT_CLASS:
                    constants[i] = new ConstantClass(i, reader, fm);
                    break;
                case ClassFileConst.CONSTANT_FIELDREF:
                    constants[i] = new ConstantFieldref(i, reader, fm);
                    break;
                case ClassFileConst.CONSTANT_METHODREF:
                    constants[i] = new ConstantMethodref(i, reader, fm);
                    break;
                case ClassFileConst.CONSTANT_INTERFACEMETHODREF:
                    constants[i] = new ConstantInterfaceMethodref(i, reader, fm);
                    break;
                case ClassFileConst.CONSTANT_STRING:
                    constants[i] = new ConstantString(i, reader, fm);
                    break;
                case ClassFileConst.CONSTANT_INTEGER:
                    constants[i] = new ConstantInteger(i, reader, fm);
                    break;
                case ClassFileConst.CONSTANT_FLOAT:
                    constants[i] = new ConstantFloat(i, reader, fm);
                    break;
                case ClassFileConst.CONSTANT_LONG:
                    constants[i] = new ConstantLong(i, reader, fm);
                    i++;
                    break;
                case ClassFileConst.CONSTANT_DOUBLE:
                    constants[i] = new ConstantDouble(i, reader, fm);
                    i++;
                    break;
                case ClassFileConst.CONSTANT_NAMEANDTYPE:
                    constants[i] = new ConstantNameAndType(i, reader, fm);
                    break;
                case ClassFileConst.CONSTANT_UTF8:
                    constants[i] = new ConstantUtf8(i, reader, fm);
                    break;
                case ClassFileConst.CONSTANT_METHODHANDLE:
                    constants[i] = new ConstantMethodHandle(i, reader, fm);
                    break;
                case ClassFileConst.CONSTANT_METHODTYPE:
                    constants[i] = new ConstantMethodType(i, reader, fm);
                    break;
                case ClassFileConst.CONSTANT_DYNAMIC:
                    constants[i] = new ConstantDynamic(i, reader, fm);
                    break;
                case ClassFileConst.CONSTANT_INVOKEDYNAMIC:
                    constants[i] = new ConstantInvokeDynamic(i, reader, fm);
                    break;
                case ClassFileConst.CONSTANT_MODULE:
                    constants[i] = new ConstantModule(i, reader, fm);
                    break;
                case ClassFileConst.CONSTANT_PACKAGE:
                    constants[i] = new ConstantPackage(i, reader, fm);
                    break;
                default:
                    throw new RuntimeException("无效常量池类型" + tag);
            }
        }
        return this;
    }

}
