package pem.demo.mobilityData;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pem.demo.member.MemberService;

import java.sql.SQLException;

@Service
@RequiredArgsConstructor
public class MBService {
    @Autowired
    MBRepository mbRepository;
    @Autowired
    MemberService memberService;

    public void add(MobilityData mb) {
        mbRepository.save(mb);
    }


    public void saveDataByFile(String filePath) {
        GetDataByFile getDataByFile = new GetDataByFile(mbRepository, memberService);
        getDataByFile.run(filePath);
    }


    public void batchInsertByFile(String filePath) throws SQLException {
        CreateMBDataByJdbc createMBDataByJdbc = new CreateMBDataByJdbc(memberService);
        createMBDataByJdbc.run(filePath);
    }



}
