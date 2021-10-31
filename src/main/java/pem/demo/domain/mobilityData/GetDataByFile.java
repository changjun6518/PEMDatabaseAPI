package pem.demo.domain.mobilityData;

import org.springframework.stereotype.Component;
import pem.demo.domain.member.Member;
import pem.demo.domain.member.MemberService;
import pem.demo.domain.mobilityData.dao.MBRepository;
import pem.demo.domain.mobilityData.dao.MobilityData;
import pem.demo.domain.mobilityData.service.JdbcService;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Component
public class GetDataByFile {

    private final MemberService memberService;
    private final MBRepository mbRepository;
    private final JdbcService jdbcService;

    public GetDataByFile(MemberService memberService, MBRepository mbRepository, JdbcService jdbcService) {
        this.memberService = memberService;
        this.mbRepository = mbRepository;
        this.jdbcService = jdbcService;
    }

    private Member getByUserNameOnFile(String filePath) {
        String userName = "";
        try {
            String[] dirNameSplit = filePath.split("/");
            userName = dirNameSplit[dirNameSplit.length - 1];       //get user name (the last directory name)
            System.out.println("user : " + userName);
        } catch (Exception e) {
            System.out.println("not found user");
        }

        return memberService.findUserByUserName(userName);
    }


    private void saveData(String filePath, Member user) {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
        String line;
        List<MobilityData> mobilityDataList = new ArrayList<>();
            while ((line = br.readLine()) != null) {
                MobilityData mobilityData = new MobilityData(user, line);
                mobilityDataList.add(mobilityData);
            }
            mbRepository.saveAll(mobilityDataList);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void saveDataByJdbc(String filePath, Member user) {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            List<MobilityData> mobilityDataList = new ArrayList<>();
            while ((line = br.readLine()) != null) {
                MobilityData mobilityData = new MobilityData(user, line);
                mobilityDataList.add(mobilityData);
            }
            mbRepository.saveAll(mobilityDataList);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void run(String filePath) throws SQLException {
        CreateMBDataByJdbc createMBDataByJdbc = new CreateMBDataByJdbc(memberService);

        Member user = getByUserNameOnFile(filePath);
        File dir = new File(filePath);
        File files[] = dir.listFiles();
        createMBDataByJdbc.connectDB();
        for (int i = 0; i < files.length; i++) {
            if (files[i].getPath().endsWith(".txt") && !jdbcService.validateDuplicate(files[i].getName())) {
                createMBDataByJdbc.batchInsert2(files[i].getPath(), user.getId());
            }
        }
        createMBDataByJdbc.closeDB();
    }
}
