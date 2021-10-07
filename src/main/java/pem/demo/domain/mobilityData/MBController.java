package pem.demo.domain.mobilityData;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import pem.demo.util.FileUtil;
import pem.demo.util.PythonExecution;

import javax.swing.filechooser.FileSystemView;
import java.util.List;

@Controller
public class MBController {
    @Autowired
    MBService mbService;

    @GetMapping("getMobilityData")
    public String saveDataByFile(Model model) {
        model.addAttribute("data", "chang");
        return "mobilityData";
    }
    @PostMapping("/multifiles")
    public String uploadSingle(@RequestParam("files") List<MultipartFile> files ,Model model) throws Exception {
        String rootPath = FileSystemView.getFileSystemView().getHomeDirectory().toString();
        String os = System.getProperty("os.name").toLowerCase();
        String basePath = "";
        FileUtil fileUtil = new FileUtil();
        if (os.contains("win")) {
            basePath = rootPath + "\\pemDB\\clusterPython\\";
            String userName = fileUtil.getUserNameByRawDataFile(files.get(0));
            fileUtil.setBasePathAndOsPathSign(basePath, "\\");
            fileUtil.checkNecessaryFolder(basePath, userName);


            mbService.batchInsertByFiles(files, fileUtil);
            fileUtil.createListFile();
//            PythonExecution.convertRawToKML(userName);
        } else if (os.contains("nix") || os.contains("nux") || os.contains("aix")) {
            basePath = "/home/PEM/jenkins/workspace/devOps/clusterPython/";
            String userName = fileUtil.getUserNameByRawDataFile(files.get(0));
            fileUtil.setBasePathAndOsPathSign(basePath, "/");
            fileUtil.checkNecessaryFolder(basePath, userName);


            mbService.batchInsertByFiles(files, fileUtil);
            fileUtil.createListFile();
        }
        model.addAttribute("data", basePath+"에 "+ "data 저장이 완료 되었습니다!");
        return "mobilityData";
    }
}
