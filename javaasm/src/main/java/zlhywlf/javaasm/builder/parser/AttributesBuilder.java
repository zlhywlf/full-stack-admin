package zlhywlf.javaasm.builder.parser;

import lombok.RequiredArgsConstructor;
import zlhywlf.javaasm.helper.BytesReader;
import zlhywlf.javaasm.model.Attribute;
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

    public Attribute[] build() {
        Attribute[] attributes = new Attribute[count];
        for (int i = 0; i < count; i++) {
            Attribute attribute = new Attribute();
            attribute.setId(i);
            attribute.setAttributeNameIndexBytes(reader.next2());
            attribute.setAttributeLengthBytes(reader.next4());
            attribute.setInfoBytes(reader.next(ByteUtil.toUnsignedInt(attribute.getAttributeLengthBytes())));
            attributes[i] = attribute;
        }
        return attributes;
    }

}
