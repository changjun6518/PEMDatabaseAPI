package pem.demo.mobilityData;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.swing.filechooser.FileSystemView;
import java.io.File;
import java.util.List;

@Controller
public class MBController {
    @Autowired
    MBService mbService;

    @GetMapping("getDataByFile")
    public String saveDataByFile(Model model) {
        model.addAttribute("data", "chang");
        return "hello";
    }


    @PostMapping("/multifiles")
    public String uploadSingle(@RequestParam("files") List<MultipartFile> files ,Model model) throws Exception {
        model.addAttribute("data", "data 저장이 완료 되었습니다!");
        String rootPath = FileSystemView.getFileSystemView().getHomeDirectory().toString();
        String basePath = rootPath + "\\" + "single";
        mbService.batchInsertByFiles(files, basePath);
        return "hello";
    }
}
