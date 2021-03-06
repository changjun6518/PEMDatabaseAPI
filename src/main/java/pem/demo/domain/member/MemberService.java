package pem.demo.domain.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class MemberService {
    @Autowired
    MemberRepository memberRepository;

    @Transactional
    public Member findUserByUserName(String userName) {
        Optional<Member> memberByName = memberRepository.findByName(userName);
        return memberByName.orElseGet(() -> memberRepository.saveAndFlush(new Member(userName)));
    }




}
