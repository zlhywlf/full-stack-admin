package zlhywlf.javaasm.visitor.simple;

import java.util.Formatter;
import java.util.function.BiConsumer;

import lombok.RequiredArgsConstructor;
import zlhywlf.javaasm.model.Attribute;
import zlhywlf.javaasm.util.ByteUtil;
import zlhywlf.javaasm.util.FormatUtil;
import zlhywlf.javaasm.util.HexUtil;

@RequiredArgsConstructor
public class AttributeSimpleVisitor implements BiConsumer<Formatter, Attribute> {

    private final BiConsumer<Formatter, Attribute> raw;

    @Override
    public void accept(Formatter t, Attribute u) {
        raw.accept(t, u);
        FormatUtil.formatIndex(t, "attributeNameIndex", u.getAttributeNameIndexBytes());
        FormatUtil.formatString(t, "attributeName", u.getName());
        FormatUtil.formatNumber(t, "length", u.getAttributeLengthBytes(),
                ByteUtil.toUnsignedInt(u.getAttributeLengthBytes()));
        FormatUtil.formatString(t, "info", HexUtil.format(u.getInfoBytes()));
    }

}
