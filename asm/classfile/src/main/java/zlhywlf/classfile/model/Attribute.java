package zlhywlf.classfile.model;

import lombok.Getter;
import lombok.Setter;

/**
 * 属性
 * 
 * attribute_info {
 * u2 attribute_name_index;
 * u4 attribute_length;
 * u1 info[attribute_length];
 * }
 * 
 * @author zlhywlf
 */
@Getter
@Setter
public class Attribute {

    private int id;
    private String name;
    private byte[] attributeNameIndexBytes;
    private byte[] attributeLengthBytes;
    private byte[] infoBytes;

}
