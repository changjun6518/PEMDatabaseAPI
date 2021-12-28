package pem.demo.util;

import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;
import pem.demo.util.constant.FilePathMessage;

import java.io.*;

public class FileUtil {
    public static String basePath;
    public static String rawdataPath;
    public static String listPath;

    static {
        String os = System.getProperty("os.name").toLowerCase();
        if (os.contains("win")) {
            basePath = FilePathMessage.WINDOW_OS_BASE_PATH.getPath();
        }
        else if (os.contains("nix") || os.contains("nux") || os.contains("aix")){
            basePath = FilePathMessage.LINUX_OS_BASE_PATH.getPath();
        }
        rawdataPath = basePath + FilePathMessage.RAW_DATA_PATH.getPath();
        listPath = basePath + FilePathMessage.LIST_PATH.getPath();
    }

    public static String getFilePathByRawDataFile(MultipartFile file) {
        String userName = getUserNameByRawDataFile(file);
        return rawdataPath + userName + "/" + file.getOriginalFilename();
    }

    public static String getFilePathByIntegratedJsonFile(MultipartFile file) {
        String userName = getUserNameByIntegratedJsonFile(file);
        return basePath + FilePathMessage.CLUSTER_RESULT_PATH.getPath() + userName
                + FilePathMessage.CLUSTER_RESULT_DETAIL_PATH.getPath() + file.getOriginalFilename();
    }

    public static String getUserNameByRawDataFile(MultipartFile file) {
        String originalFilename = file.getOriginalFilename();
        String[] splitFileName = originalFilename.split("_");
        String userName = splitFileName[splitFileName.length - 1].replace(".txt", "").toLowerCase();
        return userName;
    }

    public static String getUserNameByIntegratedJsonFile(MultipartFile file) {
        String originalFilename = file.getOriginalFilename();
        String[] splitFileName = originalFilename.split("_");
        String userName = splitFileName[1].toLowerCase();
        return userName;
    }

    // basePath/rawdata/userName 폴더 만들기
    public static void checkNecessaryFolder(String userName) {
        String rawdataPath = basePath + FilePathMessage.RAW_DATA_PATH.getPath() + userName;
        File rawdataFolder = new File(rawdataPath);

        if (!rawdataFolder.exists()) {
            rawdataFolder.mkdir();
            System.out.println(rawdataPath + "폳더가 생성되었습니다!");
        }
    }

    // .txt로 끝나는 지 검증했니?
    public static void createListFile(String userName) throws IOException {
        File rawdataFolder = new File(rawdataPath + userName);
        File listFile = new File(listPath + "list_" + userName);
        if (!listFile.exists()) {
            listFile.createNewFile();
        }
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(listFile, false));
        for (File file : rawdataFolder.listFiles()) {
            bufferedWriter.write("./rawdata/" + userName + "/" + file.getName());
            bufferedWriter.newLine();
        }
        bufferedWriter.flush();
        bufferedWriter.close();
    }
}
