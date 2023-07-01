package zlhywlf.javaasm.builder.parser;

import lombok.RequiredArgsConstructor;
import zlhywlf.javaasm.helper.BytesReader;
import zlhywlf.javaasm.model.Member;
import zlhywlf.javaasm.util.ByteUtil;

/**
 * 构建字段或方法
 * 
 * @author zlhywlf
 */
@RequiredArgsConstructor
public class MembersBuilder {

    private final int count;
    private final BytesReader reader;

    public Member[] build() {
        Member[] members = new Member[count];
        for (int i = 0; i < count; i++) {
            Member member = new Member();
            member.setId(i + 1);
            member.setAccessFlagsBytes(reader.next2());
            member.setNameIndexBytes(reader.next2());
            member.setDescriptorIndexBytes(reader.next2());
            member.setAttributesCountBytes(reader.next2());
            member.setAttributes(
                    new AttributesBuilder(
                            ByteUtil.toUnsignedInt(member.getAttributesCountBytes()),
                            reader).build());
            members[i] = member;
        }
        return members;
    }

}
