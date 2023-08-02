package zlhywlf.cloud.member.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import zlhywlf.cloud.member.mapper.MemberMapper;

@Service
@AllArgsConstructor
public class MemberServiceImpl implements MemberService {

    private MemberMapper memberMapper;

    @Override
    public int count() {
        return memberMapper.count();
    }

}
