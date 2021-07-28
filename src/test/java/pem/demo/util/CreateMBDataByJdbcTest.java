package pem.demo.util;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pem.demo.domain.MemberService;

import java.sql.SQLException;

@SpringBootTest
class CreateMBDataByJdbcTest {

    @Autowired
    MemberService memberService;
    @Test
    public void DB연결하고저장확인() throws SQLException {
        String filePath = "C:\\Users\\ChangJun.Choi\\Desktop\\LAB\\em\\em\\rawdata\\bjh\\20140120_bjh.txt";

        CreateMBDataByJdbc createMBDataByJdbc = new CreateMBDataByJdbc(memberService);
        createMBDataByJdbc.run(filePath);
    }

}