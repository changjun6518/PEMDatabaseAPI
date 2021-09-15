package pem.demo.mobilityData;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import pem.demo.member.Member;
import pem.demo.member.MemberService;
import pem.demo.mobilityData.dto.MBResDto;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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


    private void batchInsert(String filePath) throws SQLException {
        CreateMBDataByJdbc createMBDataByJdbc = new CreateMBDataByJdbc(memberService);
        createMBDataByJdbc.run(filePath);
    }

    public void batchInsertByFiles(List<MultipartFile> files, String basePath) throws IOException, SQLException {
        String os = System.getProperty("os.name").toLowerCase();
        if (os.contains("win")) {
            for (MultipartFile file : files) {
                String filePath = basePath + "\\" + file.getOriginalFilename();
                File dest = new File(filePath);
                System.out.println(filePath);
                file.transferTo(dest); // 파일 업로드 작업 수행
                batchInsert(filePath);
            }
        } else if (os.contains("nix") || os.contains("nux") || os.contains("aix")) {
            for (MultipartFile file : files) {
//                String filePath = basePath + "/" + file.getOriginalFilename();
//                File dest = new File(filePath);
//                System.out.println(filePath);
//                file.transferTo(dest); // 파일 업로드 작업 수행
//                batchInsert(filePath);
                String path = System.getProperty("user.dir");
                System.out.println("Working Directory = " + path);
                Path relativePath = Paths.get("");
                String path2 = relativePath.toAbsolutePath().toString();
                System.out.println("Working Directory2 = " + path2);
            }
        }
    }


    public Page<MBResDto> getMBData(int offset, int limit, String name) {
        PageRequest pageRequest = PageRequest.of(offset, limit, Sort.Direction.ASC, "unixTime");
        Member userByUserName = memberService.findUserByUserName(name);
        Page<MobilityData> mobilityDataByMemberName = mbRepository.findMobilityDataByMemberName(userByUserName.getId(), pageRequest);
        return mobilityDataByMemberName.map(MBResDto::new);
    }



}
