package zlhywlf.javaasm.builder;

import lombok.RequiredArgsConstructor;
import zlhywlf.javaasm.helper.BytesReader;
import zlhywlf.javaasm.model.Attribute;

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
        for (int i = 1; i < count; i++) {
        }
        return attributes;
    }

}
