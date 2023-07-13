package zlhywlf.classfile.builder.parser;

import lombok.RequiredArgsConstructor;
import zlhywlf.classfile.builder.visitor.ConstantsVisitorStrategyBuilder;
import zlhywlf.classfile.helper.BytesReader;
import zlhywlf.classfile.model.Attribute;
import zlhywlf.classfile.model.Constant;
import zlhywlf.classfile.model.cp.ConstantUtf8;
import zlhywlf.classfile.util.ByteUtil;

/**
 * 构建属性
 * 
 * @author zlhywlf
 */
@RequiredArgsConstructor
public class AttributesBuilder {

    private final int count;
    private final BytesReader reader;
    private final Constant[] constantPool;

    public Attribute[] build() {
        Attribute[] attributes = new Attribute[count];
        for (int i = 0; i < count; i++) {
            byte[] indexBytes = reader.next2();
            int index = ByteUtil.toUnsignedInt(indexBytes);
            ConstantUtf8 constant = ConstantsVisitorStrategyBuilder.cast(constantPool[index], ConstantUtf8.class);
            String name = ByteUtil.toString(constant.getBytes());
            byte[] lengthBytes = reader.next4();
            Attribute attribute = new Attribute();
            attribute.setId(i);
            attribute.setName(name);
            attribute.setAttributeNameIndexBytes(indexBytes);
            attribute.setAttributeLengthBytes(lengthBytes);
            attribute.setInfoBytes(reader.next(ByteUtil.toUnsignedInt(attribute.getAttributeLengthBytes())));
            attributes[i] = attribute;
        }
        return attributes;
    }

}
