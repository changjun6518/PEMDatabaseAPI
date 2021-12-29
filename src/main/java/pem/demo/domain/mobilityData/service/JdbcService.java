package pem.demo.domain.mobilityData.service;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import pem.demo.domain.member.Member;
import pem.demo.domain.member.MemberService;
import pem.demo.domain.mobilityData.CreateMBDataByJdbc;
import pem.demo.domain.mobilityData.dao.MBRepository;
import pem.demo.domain.mobilityData.dao.MobilityData;
import pem.demo.domain.mobilityData.exception.DuplicationException;
import pem.demo.util.FileUtil;
import pem.demo.util.constant.FilePathMessage;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@Component
public class JdbcService {
    protected final MemberService memberService;
    protected final MBRepository mbRepository;
    private static final Integer YMD_INDEX = 0;

    public JdbcService(MemberService memberService, MBRepository mbRepository) {
        this.memberService = memberService;
        this.mbRepository = mbRepository;
    }

    public void setNecessaryFileAndBatchInsert(List<MultipartFile> files) throws IOException, SQLException {
        String userName = FileUtil.getUserNameByRawDataFile(files.get(0));
        FileUtil.createRawDataFolder(userName);

        batchInsertByFiles(files);
        FileUtil.createListFile(userName);
        // 나중에 이부분 트렌젝션 하게 처리해야함 AOP?
    }

    public void saveAllBy(String userName) throws SQLException {
        CreateMBDataByJdbc createMBDataByJdbc = new CreateMBDataByJdbc(memberService);
        String filePath = FilePathMessage.WINDOW_OS_RAW_DATA_PATH.getPath() + userName;
        Member user = memberService.findUserByUserName(userName);
        File dir = new File(filePath);
        File files[] = dir.listFiles();
        createMBDataByJdbc.connectDB();
        for (int i = 0; i < files.length; i++) {
            if (files[i].getPath().endsWith(".txt") && !validateDuplicate(files[i].getName())) {
                createMBDataByJdbc.batchInsert2(files[i].getPath(), user.getId());
            }
        }
        createMBDataByJdbc.closeDB();
    }

    private void batchInsertByFiles(List<MultipartFile> files) throws IOException, SQLException {
        for (MultipartFile file : files) {
            String filePath = createFile(file);
            batchInsert(filePath);
        }
    }

    private void batchInsert(String filePath) throws SQLException {
        CreateMBDataByJdbc createMBDataByJdbc = new CreateMBDataByJdbc(memberService);
        createMBDataByJdbc.run(filePath);
    }

    private String createFile(MultipartFile file) throws IOException {
        String filePath = FileUtil.getFilePathByRawDataFile(file);
        File dest = new File(filePath);
        System.out.println(filePath);
        file.transferTo(dest); // 파일 업로드 작업 수행
        return filePath;
    }

    // 중복 검증 안하고 있음 2021.12.29
    private boolean validateDuplicate(String fileName) {
        String ymd = fileName.split("_")[YMD_INDEX];
        String convertedYmd = ymd.substring(0, 4) + "|" + ymd.substring(4, 6) + "|" + ymd.substring(6, 8);

        MobilityData topByYmd = mbRepository.findFirstByYmd(convertedYmd);
        return topByYmd != null;
    }
}
