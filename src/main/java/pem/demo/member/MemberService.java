package pem.demo.member;

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
        System.out.println("1");
        Optional<Member> memberByName = memberRepository.findByName(userName);
        System.out.println("2");

        if (memberByName.isPresent()) {
            System.out.println("3");

            return memberByName.get();
        }
        System.out.println("4");

        return memberRepository.saveAndFlush(new Member(userName));
    }




}
