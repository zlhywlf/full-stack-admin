package zlhywlf.classfile.model.cp;

import lombok.Getter;
import lombok.Setter;
import zlhywlf.classfile.model.Constant;

/**
 * CONSTANT_Integer_info {
 * u1 tag;
 * u4 bytes;
 * }
 * 
 * @author zlhywlf
 */
@Getter
@Setter
public class ConstantInteger extends Constant {

    private byte[] bytes;

}
