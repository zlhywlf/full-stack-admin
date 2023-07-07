package zlhywlf.classfile.model.cp;

import lombok.Getter;
import lombok.Setter;
import zlhywlf.classfile.model.Constant;

/**
 * CONSTANT_Package_info {
 * u1 tag;
 * u2 name_index;
 * }
 * 
 * @author zlhywlf
 */
@Getter
@Setter
public class ConstantPackage extends Constant {

    private byte[] nameIndexBytes;

}
