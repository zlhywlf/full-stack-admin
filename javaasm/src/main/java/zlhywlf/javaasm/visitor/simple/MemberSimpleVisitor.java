package zlhywlf.javaasm.visitor.simple;

import java.util.Formatter;
import java.util.function.BiConsumer;

import lombok.RequiredArgsConstructor;
import zlhywlf.javaasm.model.Attribute;
import zlhywlf.javaasm.model.Member;
import zlhywlf.javaasm.util.ByteUtil;
import zlhywlf.javaasm.util.FormatUtil;

@RequiredArgsConstructor
public class MemberSimpleVisitor implements BiConsumer<Formatter, Member> {

    private final BiConsumer<Formatter, Member> raw;
    private final BiConsumer<Formatter, Attribute> attr;

    @Override
    public void accept(Formatter t, Member u) {
        raw.accept(t, u);
        FormatUtil.formatBinary(t, "accessFlags", u.getAccessFlagsBytes());
        FormatUtil.formatIndex(t, "nameIndex", u.getNameIndexBytes());
        FormatUtil.formatIndex(t, "descriptor", u.getDescriptorIndexBytes());
        FormatUtil.formatNumber(t, "attributesCount", u.getAttributesCountBytes(),
                ByteUtil.toUnsignedInt(u.getAttributesCountBytes()));
        Attribute[] attributes = u.getAttributes();
        for (Attribute a : attributes) {
            attr.accept(t, a);
        }
    }

}
