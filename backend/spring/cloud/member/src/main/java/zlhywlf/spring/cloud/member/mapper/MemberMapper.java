package zlhywlf.spring.cloud.member.mapper;

import java.util.List;
import zlhywlf.spring.cloud.member.model.Member;

public interface MemberMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Member row);

    Member selectByPrimaryKey(Long id);

    List<Member> selectAll();

    int updateByPrimaryKey(Member row);
}