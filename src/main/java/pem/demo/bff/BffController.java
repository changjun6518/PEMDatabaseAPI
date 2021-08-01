package pem.demo.bff;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.swing.filechooser.FileSystemView;
import java.util.List;

@Controller
public class BffController {

    @Autowired
    BffService bffService;

    @GetMapping("getBffData")
    public String saveDataByFile(Model model) {
        model.addAttribute("data", "chang");
        return "bffData";
    }

    @PostMapping("/bff/multifiles")
    public String uploadSingle(@RequestParam("files") List<MultipartFile> files , Model model) throws Exception {
        model.addAttribute("data", "data 저장이 완료 되었습니다!");
        String rootPath = FileSystemView.getFileSystemView().getHomeDirectory().toString();
        String basePath = rootPath + "\\" + "single";
        bffService.saveBffDataByCsvFiles(files, basePath);
        return "bffData";
    }
}
