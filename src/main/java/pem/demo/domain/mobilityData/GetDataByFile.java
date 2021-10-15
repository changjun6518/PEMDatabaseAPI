package pem.demo.domain.mobilityData;

import org.springframework.stereotype.Component;
import pem.demo.domain.member.Member;
import pem.demo.domain.member.MemberService;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

@Component
public class GetDataByFile {

    private MemberService memberService;
    private MBRepository mbRepository;

    public GetDataByFile(MBRepository mbRepository, MemberService memberService) {
        this.mbRepository = mbRepository;
        this.memberService = memberService;
    }

    public Member getByUserNameOnFile(String filePath) {
        String userName = "";
        try {
            String[] dirNameSplit = filePath.split("_");
            userName = dirNameSplit[dirNameSplit.length - 1].replace(".txt","");       //get user name (the last directory name)
            System.out.println("user : " + userName);
        } catch (Exception e) {
            System.out.println("not found user");
        }

        return memberService.findUserByUserName(userName);
    }


    public void saveData(String filePath, Member user) {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
        String line;
        List<MobilityData> mobilityDataList = new ArrayList<>();
            while ((line = br.readLine()) != null) {
                MobilityData mobilityData = new MobilityData(user, line);
                //data upload
                mobilityDataList.add(mobilityData);
//                    mbRepository.save(mobilityData);
            }
            mbRepository.saveAll(mobilityDataList);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void run(String filePath) {
        Member user = getByUserNameOnFile(filePath);
        this.saveData(filePath, user);
    }
}
