package zlhywlf.classfile.model;

import lombok.Getter;
import lombok.Setter;
import zlhywlf.classfile.cnst.ClassFileConst;
import zlhywlf.classfile.util.ByteUtil;

/**
 * ClassFile {
 * u4 magic;
 * u2 minor_version;
 * u2 major_version;
 * u2 constant_pool_count;
 * cp_info constant_pool[constant_pool_count-1];
 * u2 access_flags;
 * u2 this_class;
 * u2 super_class;
 * u2 interfaces_count;
 * u2 interfaces[interfaces_count];
 * u2 fields_count;
 * field_info fields[fields_count];
 * u2 methods_count;
 * method_info methods[methods_count];
 * u2 attributes_count;
 * attribute_info attributes[attributes_count];
 * }
 * 
 * @author zlhywlf
 */
@Getter
@Setter
public class ClassFile {

    private Node magic;
    private Node minorVersion;
    private Node majorVersion;
    private Node constantPoolCount;
    private Constant[] constantPool;
    private Node accessFlags;
    private Node thisClass;
    private Node superClass;
    private Node interfacesCount;
    private Interface[] interfaces;
    private Node fieldsCount;
    private Member[] fields;
    private Node methodsCount;
    private Member[] methods;
    private Node attributesCount;
    private Attribute[] attributes;

    public void setMagic(Node magic) {
        int value = ByteUtil.toInt(magic.getBytes());
        if (value != ClassFileConst.MAGIC_VALUE) {
            throw new RuntimeException("不是标准的 .class 文件");
        }
        this.magic = magic;
    }

}
