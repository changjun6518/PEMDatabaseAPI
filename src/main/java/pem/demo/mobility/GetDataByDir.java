package pem.demo.mobility;

import pem.demo.domain.MobilityData;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class GetDataByDir {

    public File getDirList(String dirName) {
        File dir = new File(dirName);
        return dir;
    }

    public String getUserName(String dirName) {
        String user = "";
        try {
            String[] dirNameSplit = dirName.split("\\\\");
            user = dirNameSplit[dirNameSplit.length - 1];       //get user name (the last directory name)
            System.out.println("user : " + user);
        } catch (Exception e) {
            System.out.println("not found user");
        }
        return user;
    }

    public void saveData(File dir, String user, MBRepository mbRepository) {
        for (File filePath : dir.listFiles()) {         //get files in dir
            System.out.println("filePath : " + filePath);
            try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
                String line;
                List<MobilityData> mobilityDataList = new ArrayList<>();
                while ((line = br.readLine()) != null) {
                    MobilityData mobilityData = new MobilityData(user, line);
                    //data upload
                    mobilityDataList.add(mobilityData);
//                    mbRepository.save(mobilityData);
                }
                mbRepository.saveAll(mobilityDataList);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return;
        }
    }

    public void run(String dirName, MBRepository mbRepository) {
        File dir = this.getDirList(dirName);
        String user = this.getUserName(dirName);
        this.saveData(dir, user, mbRepository);
    }
}
