package zlhywlf.javaasm.visitor.simple;

import java.util.Formatter;
import java.util.function.BiConsumer;

import lombok.RequiredArgsConstructor;
import zlhywlf.javaasm.model.Attribute;
import zlhywlf.javaasm.model.Member;
import zlhywlf.javaasm.util.ByteUtil;

@RequiredArgsConstructor
public class MemberSimpleVisitor implements BiConsumer<Formatter, Member> {

    private final BiConsumer<Formatter, Member> raw;
    private final BiConsumer<Formatter, Attribute> attr;

    @Override
    public void accept(Formatter t, Member u) {
        raw.accept(t, u);
        t.format("          access_flags = 0b%s, name --> #%03d, descriptor --> #%03d, attributesCount = %d%n",
                Integer.toBinaryString(1 << 16 | ByteUtil.toUnsignedInt(u.getAccessFlagsBytes())).substring(1),
                ByteUtil.toUnsignedInt(u.getNameIndexBytes()),
                ByteUtil.toUnsignedInt(u.getDescriptorIndexBytes()),
                ByteUtil.toUnsignedInt(u.getAttributesCountBytes()));

        Attribute[] attributes = u.getAttributes();
        for (Attribute a : attributes) {
            attr.accept(t, a);
        }
    }

}
