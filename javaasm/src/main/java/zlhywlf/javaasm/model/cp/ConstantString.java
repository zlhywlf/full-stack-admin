package zlhywlf.javaasm.model.cp;

import lombok.Getter;
import lombok.Setter;
import zlhywlf.javaasm.model.Constant;

/**
 * CONSTANT_String_info {
 * u1 tag;
 * u2 string_index;
 * }
 * 
 * @author zlhywlf
 */
@Setter
@Getter
public class ConstantString extends Constant {

    private byte[] stringIndexBytes;

}
