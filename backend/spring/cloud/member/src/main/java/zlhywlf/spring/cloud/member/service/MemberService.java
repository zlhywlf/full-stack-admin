package zlhywlf.spring.cloud.member.service;

import zlhywlf.spring.cloud.member.model.Member;
import zlhywlf.spring.cloud.member.request.MemberRequest;

import java.util.List;

public interface MemberService {
    List<Member> selectAll();

    Long register(MemberRequest request);
}