package zlhywlf.spring.cloud.member.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import zlhywlf.spring.cloud.member.mapper.MemberMapper;
import zlhywlf.spring.cloud.member.model.Member;
import zlhywlf.spring.cloud.member.request.MemberRequest;

import java.util.List;

@Service
@AllArgsConstructor
public class MemberServiceImpl implements MemberService {
    private MemberMapper memberMapper;

    @Override
    public List<Member> selectAll() {
        return memberMapper.selectAll();
    }

    @Override
    public Long register(MemberRequest request) {
        Member member = new Member();
        member.setId(System.currentTimeMillis());
        member.setMobile(request.getMobile());
        memberMapper.insert(member);
        return member.getId();
    }
}