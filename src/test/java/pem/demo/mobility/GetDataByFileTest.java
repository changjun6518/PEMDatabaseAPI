package pem.demo.mobility;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pem.demo.domain.Member;
import pem.demo.domain.MemberService;

import static org.junit.jupiter.api.Assertions.*;

class GetDataByFileTest {

    @Autowired
    MemberService memberService;

    @Autowired
    MBRepository mbRepository;
    @Test
    public void userNameCheck() {
        GetDataByFile getDataByFile = new GetDataByFile(mbRepository, memberService);
        Member user = getDataByFile.getByUserNameOnFile("20200302_OMG");
    }
}