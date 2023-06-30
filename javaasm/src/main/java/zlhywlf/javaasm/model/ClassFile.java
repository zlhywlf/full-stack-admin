package zlhywlf.javaasm.model;

import lombok.Getter;
import lombok.Setter;

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
    private Node[] constantPool;
    private Node accessFlags;
    private Node thisClass;
    private Node superClass;
    private Node interfacesCount;
    private Node[] interfaces;
    private Node fieldsCount;
    private Node[] fields;
    private Node methodsCount;
    private Node[] methods;
    private Node attributesCount;
    private Node[] attributes;
}
