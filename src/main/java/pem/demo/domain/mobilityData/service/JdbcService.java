package pem.demo.domain.mobilityData.service;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import pem.demo.domain.member.MemberService;
import pem.demo.domain.mobilityData.CreateMBDataByJdbc;
import pem.demo.util.FileUtil;

import javax.swing.filechooser.FileSystemView;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@Component
public class JdbcService {
    private static final String os = System.getProperty("os.name").toLowerCase();
//    private static final String rootPath = FileSystemView.getFileSystemView().getHomeDirectory().toString();
    private static final String rootPath = "D:/코딩/자바";
    private static String basePath;
    private final FileUtil fileUtil = new FileUtil();
    protected final MemberService memberService;

    public JdbcService(MemberService memberService) {
        this.memberService = memberService;
        setBasePath();
    }

    public void setNecessaryFileAndBatchInsert(List<MultipartFile> files) throws IOException, SQLException {
        String userName = fileUtil.getUserNameByRawDataFile(files.get(0));
        fileUtil.setBasePath(basePath);
        fileUtil.checkNecessaryFolder(basePath, userName); // rawdata 폴더에 User 만들어주는 부분

        batchInsertByFiles(files);
        fileUtil.createListFile();                      // List_User를 만들어주는 부분

        // 나중에 이부분 트렌젝션 하게 처리해야함 AOP?
    }

    protected void batchInsertByFiles(List<MultipartFile> files) throws IOException, SQLException {
        for (MultipartFile file : files) {
            String filePath = createFile(file);
            batchInsert(filePath);
        }
    }

    protected void batchInsert(String filePath) throws SQLException {
        CreateMBDataByJdbc createMBDataByJdbc = new CreateMBDataByJdbc(memberService);
        createMBDataByJdbc.run(filePath);
    }


    protected String createFile(MultipartFile file) throws IOException {
        String filePath = fileUtil.getRawdataPath() + fileUtil.getUserName() + fileUtil.getOsPathSign() + file.getOriginalFilename();
        File dest = new File(filePath);
        System.out.println(filePath);
        file.transferTo(dest); // 파일 업로드 작업 수행
        return filePath;
    }

    protected void setBasePath() {
        if (os.contains("win")) {
            basePath = rootPath + "/pemDB/clusterPython/";
        }
        else if (os.contains("nix") || os.contains("nux") || os.contains("aix")){
        basePath = "/home/PEM/jenkins/workspace/devOps/clusterPython/";
        }
    }
}
