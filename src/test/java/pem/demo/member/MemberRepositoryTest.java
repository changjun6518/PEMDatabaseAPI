package pem.demo.member;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class MemberRepositoryTest {

    @Autowired
    MemberRepository memberRepository;

    @DisplayName("member찾기")
    @Test
    void findMemberTest() {
        Optional<Member> omg = memberRepository.findByName("omg");
        if (omg.isPresent()) {
            System.out.println(omg.get().getName());
        }
        System.out.println(omg);
    }
}