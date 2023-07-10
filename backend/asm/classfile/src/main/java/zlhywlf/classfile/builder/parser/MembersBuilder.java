package zlhywlf.classfile.builder.parser;

import lombok.RequiredArgsConstructor;
import zlhywlf.classfile.helper.BytesReader;
import zlhywlf.classfile.model.Constant;
import zlhywlf.classfile.model.Member;
import zlhywlf.classfile.util.ByteUtil;

/**
 * 构建字段或方法
 * 
 * @author zlhywlf
 */
@RequiredArgsConstructor
public class MembersBuilder {

    private final int count;
    private final BytesReader reader;
    private final Constant[] constantPool;

    public Member[] build() {
        Member[] members = new Member[count];
        for (int i = 0; i < count; i++) {
            Member member = new Member();
            member.setId(i);
            member.setAccessFlagsBytes(reader.next2());
            member.setNameIndexBytes(reader.next2());
            member.setDescriptorIndexBytes(reader.next2());
            member.setAttributesCountBytes(reader.next2());
            member.setAttributes(
                    new AttributesBuilder(
                            ByteUtil.toUnsignedInt(member.getAttributesCountBytes()),
                            reader,
                            constantPool).build());
            members[i] = member;
        }
        return members;
    }

}
