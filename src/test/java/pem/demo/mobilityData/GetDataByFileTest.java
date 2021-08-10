package pem.demo.mobilityData;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import pem.demo.member.Member;
import pem.demo.member.MemberService;

class GetDataByFileTest {

    @Autowired
    MemberService memberService;

    @Autowired
    MBRepository mbRepository;
//    @Test
//    public void userNameCheck() {
//        GetDataByFile getDataByFile = new GetDataByFile(mbRepository, memberService);
//        Member user = getDataByFile.getByUserNameOnFile("20200302_OMG");
//    }
}