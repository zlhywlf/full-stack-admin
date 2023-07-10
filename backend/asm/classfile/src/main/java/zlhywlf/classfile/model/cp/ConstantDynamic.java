package zlhywlf.classfile.model.cp;

import lombok.Getter;
import lombok.Setter;
import zlhywlf.classfile.model.Constant;

/**
 * CONSTANT_Dynamic_info {
 * u1 tag;
 * u2 bootstrap_method_attr_index;
 * u2 name_and_type_index;
 * }
 * 
 * @author zlhywlf
 */
@Getter
@Setter
public class ConstantDynamic extends Constant {

    private byte[] bootstrapMethodAttrIndexBytes;
    private byte[] nameAndTypeIndexBytes;

}
