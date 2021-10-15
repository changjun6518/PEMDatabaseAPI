package pem.demo.domain.mobilityData;

import org.springframework.beans.factory.annotation.Autowired;
import pem.demo.domain.member.MemberService;
import pem.demo.domain.mobilityData.dao.MBRepository;

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