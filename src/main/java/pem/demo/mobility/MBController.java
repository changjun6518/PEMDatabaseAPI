package pem.demo.mobility;

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

    @GetMapping("single")
    public String saveDataByFile(Model model) {
        model.addAttribute("data", "changjun!!");
        return "hello";
    }


    @PostMapping("/single2")
    public String uploadSingle(@RequestParam("files") List<MultipartFile> files) throws Exception {
        String rootPath = FileSystemView.getFileSystemView().getHomeDirectory().toString();
        String basePath = rootPath + "/" + "single";
        for (MultipartFile file : files) {
            String filePath = basePath + "/" + file.getOriginalFilename();
            File dest = new File(filePath);
            System.out.println(filePath);
            file.transferTo(dest); // 파일 업로드 작업 수행
        }


        return "hello";
    }
}
