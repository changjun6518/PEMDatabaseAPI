package pem.demo.mobility;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class GetDataByFile {

    public String getUserName(String filePath) {
        String user = "";
        try {
            String[] dirNameSplit = filePath.split("_");
            user = dirNameSplit[dirNameSplit.length - 1];       //get user name (the last directory name)
            System.out.println("user : " + user);
        } catch (Exception e) {
            System.out.println("not found user");
        }
        return user;
    }


    public void saveData(File filePath, String user, MBRepository mbRepository) {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
        String line;
        List<MobilityData> mobilityDataList = new ArrayList<>();
            while ((line = br.readLine()) != null) {
                System.out.println(line);
                MobilityData mobilityData = new MobilityData(user, line);
                //data upload
                mobilityDataList.add(mobilityData);
//                    mbRepository.save(mobilityData);
            }
            mbRepository.saveAll(mobilityDataList);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void run(String filePath, MBRepository mbRepository) {
        String user = getUserName(filePath);
        this.saveData(new File(filePath), user, mbRepository);
    }
}
