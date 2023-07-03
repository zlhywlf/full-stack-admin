package zlhywlf.javaasm.builder.parser;

import lombok.RequiredArgsConstructor;
import zlhywlf.javaasm.builder.visitor.ConstantsVisitorStrategyBuilder;
import zlhywlf.javaasm.helper.BytesReader;
import zlhywlf.javaasm.model.Attribute;
import zlhywlf.javaasm.model.Constant;
import zlhywlf.javaasm.model.cp.ConstantUtf8;
import zlhywlf.javaasm.util.ByteUtil;

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
