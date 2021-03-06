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

    private final MBService mbService;

    private final JdbcService jdbcService;

    public MBController(MBService mbService, JdbcService jdbcService) {
        this.mbService = mbService;
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

        model.addAttribute("data", "저장 성공");
        return "mobilityData";
    }

//    @DeleteMapping
}
