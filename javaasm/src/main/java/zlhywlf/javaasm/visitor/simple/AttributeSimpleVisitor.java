package zlhywlf.javaasm.visitor.simple;

import java.util.Formatter;
import java.util.function.BiConsumer;

import lombok.RequiredArgsConstructor;
import zlhywlf.javaasm.model.Attribute;
import zlhywlf.javaasm.util.ByteUtil;

@RequiredArgsConstructor
public class AttributeSimpleVisitor implements BiConsumer<Formatter, Attribute> {

    private final BiConsumer<Formatter, Attribute> raw;

    @Override
    public void accept(Formatter t, Attribute u) {
        raw.accept(t, u);
        int index = ByteUtil.toUnsignedInt(u.getAttributeNameIndexBytes());
        t.format("          attributeNameIndex --> #%03d(%s), length = %d%n", index, u.getName(),
                ByteUtil.toUnsignedInt(u.getAttributeLengthBytes()));

    }

}
