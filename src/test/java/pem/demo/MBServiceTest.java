package pem.demo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import pem.demo.mobilityData.MBService;
import pem.demo.mobilityData.MobilityData;

@SpringBootTest
@Transactional
@Rollback(value = false)
public class MBServiceTest {

    @Autowired
    MBService mbService;

//    @Test
//    public void 테스트() {
//
//        String temp = "2020|03|01\t06:37:05\t1583012225\t37.545035\t126.922493";
//        MobilityData mb = new MobilityData(temp);
//        mbService.add(mb);
//    }
}