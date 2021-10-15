package pem.demo;

import org.springframework.beans.factory.annotation.Autowired;
import pem.demo.domain.mobilityData.service.MBService;

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
//    @Test
//    public void test22() throws IOException {
//        MultipartFile multipartFile = new MockMultipartFile("20191201_OMG.txt",
//                new FileInputStream(new File("C:\\Users\\ChangJun.Choi\\Desktop\\LAB\\em\\em\\rawdata\\OMG\\20191201_OMG.txt")));
//        System.out.println("multipartFile = " + multipartFile.getOriginalFilename());
//
//    }
}