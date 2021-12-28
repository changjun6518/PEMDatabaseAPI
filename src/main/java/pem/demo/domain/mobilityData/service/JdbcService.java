package pem.demo.domain.mobilityData.service;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
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
    private static StringBuilder message;
    private static StringBuilder duplicatedFile = new StringBuilder();

    public JdbcService(MemberService memberService, MBRepository mbRepository) {
        this.memberService = memberService;
        this.mbRepository = mbRepository;
    }

    public void setNecessaryFileAndBatchInsert(List<MultipartFile> files) throws IOException, SQLException {
        String userName = FileUtil.getUserNameByRawDataFile(files.get(0));
        FileUtil.checkNecessaryFolder(userName); // rawdata 폴더에 User 만들어주는 부분

        batchInsertByFiles(files);
        FileUtil.createListFile(userName);                      // List_User를 만들어주는 부분

        // 나중에 이부분 트렌젝션 하게 처리해야함 AOP?
    }

    protected void batchInsertByFiles(List<MultipartFile> files) throws IOException, SQLException {
        message = new StringBuilder("data 저장이 완료 되었습니다!");
        try {
            for (MultipartFile file : files) {
                if (validateDuplicate(file.getOriginalFilename())) {
                    initMessage();
                    duplicatedFile = new StringBuilder();
                    duplicatedFile.append(file.getOriginalFilename());
                    continue;
//                    throw new DuplicationException();
                }
                String filePath = createFile(file);
                batchInsert(filePath);
            }
        } catch (DuplicationException e) {
            System.out.println(e.getMessage());
        } finally {
            settingMessage();
        }
    }

    protected void batchInsert(String filePath) throws SQLException {
        CreateMBDataByJdbc createMBDataByJdbc = new CreateMBDataByJdbc(memberService);
        createMBDataByJdbc.run(filePath);
    }

    protected String createFile(MultipartFile file) throws IOException {
        String filePath = FileUtil.getFilePathByRawDataFile(file);
        File dest = new File(filePath);
        System.out.println(filePath);
        file.transferTo(dest); // 파일 업로드 작업 수행
        return filePath;
    }

    public boolean validateDuplicate(String fileName) {
        String ymd = fileName.split("_")[YMD_INDEX];
        String convertedYmd = ymd.substring(0, 4) + "|" + ymd.substring(4, 6) + "|" + ymd.substring(6, 8);

        MobilityData topByYmd = mbRepository.findFirstByYmd(convertedYmd);
        return topByYmd != null;
    }


    public String getMessage() {
        return message.toString();
    }

    private void initMessage() {
            message.setLength(0);
    }

    private void settingMessage() {
        message.append(duplicatedFile);
        message.append(" , ");
        message.append("데이터가 중복되었습니다");
        message.append("\n");
    }
}
