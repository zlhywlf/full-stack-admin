package zlhywlf.classfile.model;

import lombok.Getter;
import lombok.Setter;

/**
 * 字段与方法
 * <p>
 * field_info and method_info {
 * u2 access_flags;
 * u2 name_index;
 * u2 descriptor_index;
 * u2 attributes_count;
 * attribute_info attributes[attributes_count];
 * }
 *
 * @author zlhywlf
 */
@Getter
@Setter
public class Member {

    private int id;
    private byte[] accessFlagsBytes;
    private byte[] nameIndexBytes;
    private byte[] descriptorIndexBytes;
    private byte[] attributesCountBytes;
    private Attribute[] attributes;

}
