package zlhywlf.javaasm.builder.parser;

import lombok.RequiredArgsConstructor;
import zlhywlf.javaasm.helper.BytesReader;
import zlhywlf.javaasm.model.Interface;

/**
 * 构建接口
 * 
 * @author zlhywlf
 */
@RequiredArgsConstructor
public class InterfacesBuilder {

    private final int count;
    private final BytesReader reader;

    public Interface[] build() {
        Interface[] interfaces = new Interface[count];
        for (int i = 0; i < count; i++) {
            Interface inter = new Interface();
            inter.setId(i + 1);
            inter.setBytes(reader.next2());
            interfaces[i] = inter;
        }
        return interfaces;
    }

}
