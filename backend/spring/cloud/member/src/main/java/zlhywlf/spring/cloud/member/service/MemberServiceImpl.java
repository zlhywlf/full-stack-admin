package zlhywlf.spring.cloud.member.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import zlhywlf.spring.cloud.member.mapper.MemberMapper;
import zlhywlf.spring.cloud.member.model.MemberExample;

@Service
@AllArgsConstructor
public class MemberServiceImpl implements MemberService {

    private MemberMapper memberMapper;

    @Override
    public int count() {
        return (int)memberMapper.countByExample(new MemberExample());
    }

}
