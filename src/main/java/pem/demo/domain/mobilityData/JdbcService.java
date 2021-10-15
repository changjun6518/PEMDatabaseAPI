package pem.demo.domain.mobilityData;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import pem.demo.domain.member.MemberService;
import pem.demo.util.FileUtil;

import javax.swing.filechooser.FileSystemView;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@Component
public class JdbcService {
    private static final String os = System.getProperty("os.name").toLowerCase();
    private static final String rootPath = FileSystemView.getFileSystemView().getHomeDirectory().toString();
    private static String basePath;
    private final FileUtil fileUtil = new FileUtil();
    private final MemberService memberService;

    public JdbcService(MemberService memberService) {
        this.memberService = memberService;
        setBasePath();
    }

    public void setNecessaryFileAndBatchInsert(List<MultipartFile> files) throws IOException, SQLException {
        String userName = fileUtil.getUserNameByRawDataFile(files.get(0));
        fileUtil.setBasePath(basePath);
        fileUtil.checkNecessaryFolder(basePath, userName); // rawdata 폴더에 User 만들어주는 부분

        batchInsertByFiles(files, fileUtil);
        fileUtil.createListFile();                      // List_User를 만들어주는 부분

    }

    private void batchInsertByFiles(List<MultipartFile> files, FileUtil fileUtil) throws IOException, SQLException {
        for (MultipartFile file : files) {
            String filePath = createFile(file, fileUtil);
            batchInsert(filePath);
        }
    }

    private void batchInsert(String filePath) throws SQLException {
        CreateMBDataByJdbc createMBDataByJdbc = new CreateMBDataByJdbc(memberService);
        createMBDataByJdbc.run(filePath);
    }

    private String createFile(MultipartFile file, FileUtil fileUtil) throws IOException {
        String filePath = fileUtil.getRawdataPath() + fileUtil.getUserName() + fileUtil.getOsPathSign() + file.getOriginalFilename();
        File dest = new File(filePath);
        System.out.println(filePath);
        file.transferTo(dest); // 파일 업로드 작업 수행
        return filePath;
    }

    private void setBasePath() {
        if (os.contains("win")) {
            basePath = rootPath + "/pemDB/clusterPython/";
        }
        else if (os.contains("nix") || os.contains("nux") || os.contains("aix")){
        basePath = "/home/PEM/jenkins/workspace/devOps/clusterPython/";
        }
    }
}
