package pem.demo.domain.clustering.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import pem.demo.domain.clustering.service.ClusteringService;
import pem.demo.util.FileUtil;

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
        for (MultipartFile file : files) {
            clusteringService.save(files);
        }
        return "clusteringData";
    }
}