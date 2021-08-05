package pem.demo.mobilityData;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.w3c.dom.stylesheets.LinkStyle;
import pem.demo.mobilityData.dto.MBResDto;

import java.util.List;

@RestController
//@RequestMapping("/mobility")
public class MBRestController {

    @Autowired
    MBService mbService;
    @Autowired
    MBRepository mbRepository;

    @GetMapping("getData")
    public  Page<MBResDto> getData(
            @RequestParam("offset") int offset,
                                   @RequestParam("limit") int limit,
                                   @RequestParam("name") String name ) {
        return mbService.getMBData(offset, limit, name);
    }


}
