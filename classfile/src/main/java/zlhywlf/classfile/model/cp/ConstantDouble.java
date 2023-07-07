package zlhywlf.classfile.model.cp;

import lombok.Getter;
import lombok.Setter;
import zlhywlf.classfile.model.Constant;

/**
 * CONSTANT_Double_info {
 * u1 tag;
 * u4 high_bytes;
 * u4 low_bytes;
 * }
 * 
 * @author zlhywlf
 */
@Getter
@Setter
public class ConstantDouble extends Constant {

    private byte[] highBytes;
    private byte[] lowBytes;

}
