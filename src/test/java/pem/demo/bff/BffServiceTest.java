package pem.demo.bff;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pem.demo.member.Member;
import pem.demo.member.MemberService;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BffServiceTest {

    @Autowired
    BffRepository bffRepository;
    @Autowired
    MemberService memberService;
//    @Test
//    public void excelfileTest() {
////        List<List<String>> ret = new ArrayList<List<String>>();
//        BufferedReader br = null;
//
//        try{
//            br = Files.newBufferedReader(Paths.get("C:\\Users\\ChangJun.Choi\\Desktop\\쥬피터홈\\bff docs\\bff docs\\bff.csv"));
//            //Charset.forName("UTF-8");
//            String line = "";
//            String firstLine = br.readLine();
//            System.out.println(firstLine);
//            while ((line = br.readLine()) != null) {
//                ArrayList<Float> bffFeatures = new ArrayList<>();
//                String array[] = line.split(",");
//                String name = array[0];
//                Member member = memberService.findUserByUserName(name);
//                for (int i = 1; i < array.length; i++) {
//                    float bffFeature = Float.parseFloat(array[i]);
//                    bffFeatures.add(bffFeature);
//                }
//                bffRepository.save(new Bff(member, bffFeatures));
//                break;
//            }
//        } catch(IOException e){
//            e.printStackTrace();
//        } finally{
//            try{
//                if(br != null){
//                    br.close();
//                }
//            }catch(IOException e){
//                e.printStackTrace();
//            }
//        }
//
//    }
}