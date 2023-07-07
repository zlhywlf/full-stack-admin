package zlhywlf.classfile.model.cp;

import lombok.Getter;
import lombok.Setter;
import zlhywlf.classfile.model.Constant;

/**
 * CONSTANT_MethodHandle_info {
 * u1 tag;
 * u1 reference_kind;
 * u2 reference_index;
 * }
 * 
 * @author zlhywlf
 */
@Getter
@Setter
public class ConstantMethodHandle extends Constant {

    private byte[] referenceKindBytes;
    private byte[] referenceIndexBytes;

}
