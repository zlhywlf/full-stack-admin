package zlhywlf.javaasm.model.cp;

import lombok.Getter;
import lombok.Setter;
import zlhywlf.javaasm.model.Constant;

/**
 * CONSTANT_Module_info {
 * u1 tag;
 * u2 name_index;
 * }
 * 
 * @author zlhywlf
 */
@Getter
@Setter
public class ConstantModule extends Constant {

    byte[] nameIndexBytes;

}
