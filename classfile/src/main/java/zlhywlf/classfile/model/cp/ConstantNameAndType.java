package zlhywlf.classfile.model.cp;

import lombok.Getter;
import lombok.Setter;
import zlhywlf.classfile.model.Constant;

/**
 * CONSTANT_NameAndType_info {
 * u1 tag;
 * u2 name_index;
 * u2 descriptor_index;
 * }
 * 
 * @author zlhywlf
 */
@Getter
@Setter
public class ConstantNameAndType extends Constant {

    private byte[] nameIndexBytes;
    private byte[] descriptorIndexBytes;

}
