package zlhywlf.javaasm.model.cp;

import lombok.Getter;
import lombok.Setter;
import zlhywlf.javaasm.model.Constant;

/**
 * CONSTANT_Methodref_info {
 * u1 tag;
 * u2 class_index;
 * u2 name_and_type_index;
 * }
 * 
 * @author zlhywlf
 */
@Getter
@Setter
public class ConstantMethodref extends Constant {

    private byte[] classIndexBytes;
    private byte[] nameAndTypeIndexBytes;

}
