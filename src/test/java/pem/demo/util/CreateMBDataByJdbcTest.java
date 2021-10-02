package pem.demo.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pem.demo.domain.member.MemberService;

@SpringBootTest
class CreateMBDataByJdbcTest {

    @Autowired
    MemberService memberService;
//    @Test
//    public void DB연결하고저장확인() throws SQLException {
////        String filePath = "C:\\Users\\ChangJun.Choi\\Desktop\\single\\20200301_OMG.txt";
//        String filePath = "C:\\Users\\ChangJun.Choi\\Desktop\\LAB\\em\\em\\rawdata\\bjh\\20140116_bjh.txt";
//
//        CreateMBDataByJdbc createMBDataByJdbc = new CreateMBDataByJdbc(memberService);
//        createMBDataByJdbc.run(filePath);
//    }

}