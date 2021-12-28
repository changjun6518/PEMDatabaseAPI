package pem.demo.util;

import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

@Getter
public class FileUtil {
    private String basePath;
    private String rawdataPath;
    private String listPath;
    private final String osPathSign= "/";
    private String userName;
    private String integratedJsonPath;
    private ArrayList<String> necessaryFolders = new ArrayList<>(Arrays.asList("rawdata"));

    public void setBasePath(String basePath) {
        this.basePath = basePath;
        this.rawdataPath = basePath + "rawdata" + osPathSign;
        this.listPath = basePath + "list" + osPathSign;
    }
    
    public String getUserNameByRawDataFile(MultipartFile file) {
        String originalFilename = file.getOriginalFilename();
        String[] splitFileName = originalFilename.split("_");
        userName = splitFileName[splitFileName.length - 1].replace(".txt", "").toLowerCase();
        return userName;
    }

    public String getUserNameByIntegratedJsonFile(MultipartFile file) {
        String originalFilename = file.getOriginalFilename();
        String[] splitFileName = originalFilename.split("_");
        userName = splitFileName[1].toLowerCase();
        return userName;
    }

    // basePath/rawdata/userName 폴더 만들기
    public void checkNecessaryFolder(String basePath, String userName) {
        for (String necessaryFolder : necessaryFolders) {
            String rawdataPath = basePath + necessaryFolder + osPathSign + userName;
            File rawdataFolder = new File(rawdataPath);

            if (!rawdataFolder.exists()) {
                rawdataFolder.mkdir();
                System.out.println(rawdataPath + "폳더가 생성되었습니다!");
            }
        }
    }

    // .txt로 끝나는 지 검증했니?
    public void createListFile() throws IOException {
        File userRawdataDir = new File(rawdataPath + osPathSign + userName);
        File listFile = new File(listPath + "list_" + userName);
        if (!listFile.exists()) {
            listFile.createNewFile();
        }
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(listFile, false));
        for (File file : userRawdataDir.listFiles()) {
            bufferedWriter.write("./rawdata/" + userName + "/" + file.getName());
            bufferedWriter.newLine();
        }
        bufferedWriter.flush();
        bufferedWriter.close();
    }
}
