package pem.demo.domain.mobilityData.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import pem.demo.domain.mobilityData.service.JdbcService;
import pem.demo.domain.mobilityData.service.MBService;

import java.util.List;

@Controller
public class MBController {
    @Autowired
    MBService mbService;

    private final JdbcService jdbcService;

    public MBController(JdbcService jdbcService) {
        this.jdbcService = jdbcService;
    }

    @GetMapping("getMobilityData")
    public String saveDataByFile(Model model) {
        model.addAttribute("data", "chang");
        return "mobilityData";
    }

    @PostMapping("/multifiles")
    public String uploadSingle(@RequestParam("files") List<MultipartFile> files ,Model model) throws Exception {
        jdbcService.setNecessaryFileAndBatchInsert(files);

        model.addAttribute("data", "data 저장이 완료 되었습니다!");
        return "mobilityData";
    }
}
