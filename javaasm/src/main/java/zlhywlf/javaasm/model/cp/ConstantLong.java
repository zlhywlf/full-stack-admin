package zlhywlf.javaasm.model.cp;

import lombok.Getter;
import lombok.Setter;
import zlhywlf.javaasm.model.Constant;

/**
 * CONSTANT_Long_info {
 * u1 tag;
 * u4 high_bytes;
 * u4 low_bytes;
 * }
 * 
 * @author zlhywlf
 */
@Getter
@Setter
public class ConstantLong extends Constant {

    private byte[] highBytes;
    private byte[] lowBytes;

}
