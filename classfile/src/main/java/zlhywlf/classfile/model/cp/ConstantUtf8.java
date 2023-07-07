package zlhywlf.classfile.model.cp;

import lombok.Getter;
import lombok.Setter;
import zlhywlf.classfile.model.Constant;

/**
 * CONSTANT_Utf8_info {
 * u1 tag;
 * u2 length;
 * u1 bytes[length];
 * }
 * 
 * @author zlhywlf
 */
@Getter
@Setter
public class ConstantUtf8 extends Constant {

    private byte[] lengthBytes;
    private byte[] bytes;

}
