package pem.demo.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class MemberService {
    @Autowired
    MemberRepository memberRepository;

    @Transactional
    public Member findUserByUserName(String userName) {
        Optional<Member> memberByName = memberRepository.findMemberByName(userName);
        return memberByName.orElseGet(() -> memberRepository.save(new Member(userName)));
    }

}
