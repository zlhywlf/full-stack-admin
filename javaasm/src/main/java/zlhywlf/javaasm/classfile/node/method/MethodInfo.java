package zlhywlf.javaasm.classfile.node.method;

import java.util.Formatter;

import zlhywlf.javaasm.classfile.ClassFileReader;
import zlhywlf.javaasm.classfile.node.field.FieldInfo;

/**
 * method_info {
 * u2 access_flags;
 * u2 name_index;
 * u2 descriptor_index;
 * u2 attributes_count;
 * attribute_info attributes[attributes_count];
 * }
 * 
 * @author zlhywlf
 */
public class MethodInfo extends FieldInfo {

    public MethodInfo(int id, ClassFileReader reader, Formatter fm) {
        super(id, reader, fm);
    }

}
