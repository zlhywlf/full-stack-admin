package zlhywlf.javaasm.visitor.raw;

import java.util.Formatter;
import java.util.function.BiConsumer;

import zlhywlf.javaasm.model.Attribute;
import zlhywlf.javaasm.model.Member;
import zlhywlf.javaasm.util.ByteUtil;
import zlhywlf.javaasm.util.HexUtil;

public class MemberRawVisitor implements BiConsumer<Formatter, Member> {

    @Override
    public void accept(Formatter t, Member u) {
        byte[] bytes = ByteUtil.merge(u.getAccessFlagsBytes(), u.getNameIndexBytes(),
                u.getDescriptorIndexBytes(), u.getAttributesCountBytes());
        Attribute[] attributes = u.getAttributes();
        for (Attribute a : attributes) {
            bytes = ByteUtil.merge(bytes, a.getAttributeNameIndexBytes(), a.getAttributeLengthBytes(),
                    a.getInfoBytes());
        }
        t.format("    |%03d| %s%n", u.getId(), HexUtil.format(bytes));
    }

}
