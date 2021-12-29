package pem.demo.domain.mobilityData.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pem.demo.domain.mobilityData.dao.MBRepository;
import pem.demo.domain.mobilityData.dto.MBResDto;
import pem.demo.domain.mobilityData.service.JdbcService;
import pem.demo.domain.mobilityData.service.MBService;

import java.sql.SQLException;

@RestController
@RequestMapping("/mobility")
public class MBApi {

    @Autowired
    MBService mbService;
    @Autowired
    MBRepository mbRepository;
    @Autowired
    JdbcService jdbcService;

    @GetMapping("getData")
    public Page<MBResDto> getData(
            @RequestParam("offset") int offset,
            @RequestParam("limit") int limit,
            @RequestParam("name") String name) {
        return mbService.getMBData(offset, limit, name);
    }

    @GetMapping("saveAll")
    public ResponseEntity<String> saveMBDataAllBy(@RequestParam("name") String name) throws SQLException {
        jdbcService.saveAllBy(name);
        return ResponseEntity.status(HttpStatus.OK).body("SaveSuccess");
    }
}
