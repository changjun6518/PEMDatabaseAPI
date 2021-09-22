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
    private String osPathSign;
    private String userName;
    private ArrayList<String> necessaryFolders = new ArrayList<>(Arrays.asList("rawdata"));

    public void setBasePathAndOsPathSign(String basePath, String osPathSign) {
        this.basePath = basePath;
        this.osPathSign = osPathSign;
        this.rawdataPath = basePath + "rawdata" + osPathSign;
        this.listPath = basePath + "list" + osPathSign;
    }
    
    public String getUserNameBy(MultipartFile file) {
        String originalFilename = file.getOriginalFilename();
        String[] splitFileName = originalFilename.split("_");
        userName = splitFileName[splitFileName.length - 1].replace(".txt", "");
        return userName;
    }

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
