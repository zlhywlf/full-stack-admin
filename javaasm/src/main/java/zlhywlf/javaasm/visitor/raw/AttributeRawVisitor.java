package zlhywlf.javaasm.visitor.raw;

import java.util.Formatter;
import java.util.function.BiConsumer;

import zlhywlf.javaasm.model.Attribute;
import zlhywlf.javaasm.util.ByteUtil;
import zlhywlf.javaasm.util.HexUtil;

public class AttributeRawVisitor implements BiConsumer<Formatter, Attribute> {

    @Override
    public void accept(Formatter t, Attribute u) {
        t.format("    |%03d| %s%n", u.getId(),
                HexUtil.format(ByteUtil.merge(u.getAttributeNameIndexBytes(), u.getAttributeLengthBytes(),
                        u.getInfoBytes())));
    }

}
