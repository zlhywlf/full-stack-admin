package zlhywlf.classfile.visitor.simple;

import java.util.Formatter;
import java.util.function.BiConsumer;

import lombok.RequiredArgsConstructor;
import zlhywlf.classfile.model.Attribute;
import zlhywlf.classfile.model.Member;
import zlhywlf.classfile.util.ByteUtil;
import zlhywlf.classfile.util.FormatUtil;

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
