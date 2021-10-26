package pem.demo.domain.clustering;

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
@RequestMapping("/clustering")
public class ClusteringController {

    @Autowired
    private ClusteringService clusteringService;

    @GetMapping("")
    public String saveDataByFile(Model model) {
        model.addAttribute("data", "chang");
        return "clusteringData";
    }

    @PostMapping("/multifiles")
    public String uploadSingle(@RequestParam("files") List<MultipartFile> files) throws Exception {
//        String rootPath = FileSystemView.getFileSystemView().getHomeDirectory().toString();
//        String basePath = rootPath + "/pemDB/clusterPython/result/psy/results/integratedJSON/";
        String rootPath = "/home/PEM/jenkins/workspace/devOps/clusterPython/result/";
        String basePath = "";
        FileUtil fileUtil = new FileUtil();
        for (MultipartFile file : files) {
            fileUtil.getUserNameByIntegratedJsonFile(file);
            basePath = rootPath + fileUtil.getUserName() + "results/integratedJSON";
        }
        clusteringService.save(files, basePath);
        return "clusteringData";
    }
}