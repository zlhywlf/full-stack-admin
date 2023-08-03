package zlhywlf.spring.cloud.member.service;

import zlhywlf.spring.cloud.member.model.Member;

import java.util.List;

public interface MemberService {
    List<Member> selectAll();
}