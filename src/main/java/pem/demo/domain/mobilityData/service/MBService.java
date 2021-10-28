package pem.demo.domain.mobilityData.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import pem.demo.domain.member.Member;
import pem.demo.domain.member.MemberService;
import pem.demo.domain.mobilityData.CreateMBDataByJdbc;
import pem.demo.domain.mobilityData.GetDataByFile;
import pem.demo.domain.mobilityData.dao.MBRepository;
import pem.demo.domain.mobilityData.dao.MobilityData;
import pem.demo.domain.mobilityData.dto.MBResDto;
import pem.demo.util.FileUtil;

import javax.swing.filechooser.FileSystemView;
import java.io.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Service
public class MBService extends JdbcService{
    private final MBRepository mbRepository;

    public MBService(MemberService memberService, MBRepository mbRepository) {
        super(memberService, mbRepository);
        this.mbRepository = mbRepository;
    }


    public void add(MobilityData mb) {
        mbRepository.save(mb);
    }

    @Override
    protected void batchInsert(String filePath) throws SQLException {

        String[] dirNameSplit = filePath.split("_");
        String userName = dirNameSplit[dirNameSplit.length - 1].replace(".txt", "").toLowerCase();       //get user name (the last directory name)
        Member member = memberService.findUserByUserName(userName);
        ArrayList<MobilityData> mobilityDataList = new ArrayList<>();
        long start = System.currentTimeMillis();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                mobilityDataList.add(new MobilityData(member, line));
            }
            mbRepository.saveAll(mobilityDataList);

            long end = System.currentTimeMillis();
            System.out.printf("execution time : %f sec\n", (float) (end - start) / 1000L);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    // 자 이것만 하면 돼! 나중에 바꿔보자


    public Page<MBResDto> getMBData(int offset, int limit, String name) {
        PageRequest pageRequest = PageRequest.of(offset, limit, Sort.Direction.ASC, "unixTime");
        Member userByUserName = memberService.findUserByUserName(name);
        Page<MobilityData> mobilityDataByMemberName = mbRepository.findMobilityDataByMemberName(userByUserName.getId(), pageRequest);
        return mobilityDataByMemberName.map(MBResDto::new);
    }


    public void deleteMBData(String ymd) {
        List<MobilityData> mbDataList = mbRepository.findAllByYmd(ymd);
        mbRepository.deleteAll(mbDataList);
    }

}
