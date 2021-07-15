package pem.demo.mobility;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

class GetDataByFileTest {

    @Test
    public void userNameCheck() {
        GetDataByFile getDataByFile = new GetDataByFile();
        String userName = getDataByFile.getUserName("20200302_OMG");
        System.out.println(userName);
    }
}