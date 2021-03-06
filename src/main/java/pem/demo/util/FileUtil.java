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

    public static String getUserNameByRawDataPath(String filePath) {
        String[] splitFileName = filePath.split("_");
        String userName = splitFileName[splitFileName.length - 1].replace(".txt", "").toLowerCase();
        return userName;
    }

    public static String getUserNameByIntegratedJsonFile(MultipartFile file) {
        String originalFilename = file.getOriginalFilename();
        String[] splitFileName = originalFilename.split("_");
        String userName = splitFileName[1].toLowerCase();
        return userName;
    }

    public static void createRawDataFolder(String userName) {
        String rawdataPath = basePath + FilePathMessage.RAW_DATA_PATH.getPath() + userName;
        File rawdataFolder = new File(rawdataPath);

        if (!rawdataFolder.exists()) {
            rawdataFolder.mkdir();
            System.out.println(rawdataPath + "????????? ?????????????????????!");
        }
    }

    public static void createListFile(String userName) throws IOException {
        File rawdataFolder = new File(rawdataPath + userName);
        File listFile = new File(listPath + "list_" + userName);
        if (!listFile.exists()) {
            listFile.createNewFile();
        }
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(listFile, false));
        for (File file : rawdataFolder.listFiles()) {
            if (file.getName().endsWith(".txt")) {
                bufferedWriter.write("./rawdata/" + userName + "/" + file.getName());
                bufferedWriter.newLine();
            }
        }
        bufferedWriter.flush();
        bufferedWriter.close();
    }
}
