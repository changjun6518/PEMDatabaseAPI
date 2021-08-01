package pem.demo.mobilityData;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import pem.demo.member.MemberService;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

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


    public void batchInsert(String filePath) throws SQLException {
        CreateMBDataByJdbc createMBDataByJdbc = new CreateMBDataByJdbc(memberService);
        createMBDataByJdbc.run(filePath);
    }

    public void batchInsertByFiles(List<MultipartFile> files, String basePath) throws IOException, SQLException {
        for (MultipartFile file : files) {
            String filePath = basePath + "\\" + file.getOriginalFilename();
            File dest = new File(filePath);
            System.out.println(filePath);
            file.transferTo(dest); // 파일 업로드 작업 수행
            batchInsert(filePath);
        }
    }

}
