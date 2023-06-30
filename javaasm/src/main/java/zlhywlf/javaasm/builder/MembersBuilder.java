package zlhywlf.javaasm.builder;

import lombok.RequiredArgsConstructor;
import zlhywlf.javaasm.helper.BytesReader;
import zlhywlf.javaasm.model.Member;

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
        for (int i = 1; i < count; i++) {
        }
        return members;

    }

}
