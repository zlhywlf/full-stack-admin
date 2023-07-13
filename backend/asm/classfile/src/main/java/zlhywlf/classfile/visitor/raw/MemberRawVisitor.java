package zlhywlf.classfile.visitor.raw;

import java.util.Formatter;
import java.util.function.BiConsumer;

import zlhywlf.classfile.model.Attribute;
import zlhywlf.classfile.model.Member;
import zlhywlf.classfile.util.ByteUtil;
import zlhywlf.classfile.util.FormatUtil;

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
        FormatUtil.formatItem(t, u.getId(), bytes);
    }

}
