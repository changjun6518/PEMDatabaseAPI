package pem.demo.domain.bff;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pem.demo.domain.bff.dto.BffResDto;

import java.util.ArrayList;

@RestController
@RequestMapping("/bff")
public class BffRestController {

    @Autowired
    BffService bffService;


    @GetMapping("getDataAll")
    public ArrayList<BffResDto> getDataAll() {
        return bffService.getBffDataAll();
    }
}
