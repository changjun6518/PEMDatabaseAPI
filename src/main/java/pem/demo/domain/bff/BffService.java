package pem.demo.domain.bff;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import pem.demo.domain.bff.dto.BffResDto;
import pem.demo.domain.member.Member;
import pem.demo.domain.member.MemberService;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Service
public class BffService {
    @Autowired
    BffRepository bffRepository;
    @Autowired
    MemberService memberService;

    private void saveBffData(String filePath) throws IOException {
        BufferedReader br = Files.newBufferedReader(Paths.get(filePath));
        String line = "";
        ArrayList<Bff> bffs = new ArrayList<>();
        while ((line = br.readLine()) != null) {
            JSONObject bffJson = new JSONObject(line);
            Bff bff = Bff.builder()
                    .o(parseFloatFunc(bffJson,"o")).c(parseFloatFunc(bffJson,"c"))
                    .e(parseFloatFunc(bffJson,"e")).a(parseFloatFunc(bffJson,"a"))
                    .n(parseFloatFunc(bffJson,"n"))
                    .aaa(parseFloatFunc(bffJson,"aaa")).bbb(parseFloatFunc(bffJson,"bbb"))
                    .ccc(parseFloatFunc(bffJson,"ccc")).ddd(parseFloatFunc(bffJson,"ddd"))
                    .eee(parseFloatFunc(bffJson,"eee")).fff(parseFloatFunc(bffJson,"fff"))
                    .ggg(parseFloatFunc(bffJson,"ggg")).hhh(parseFloatFunc(bffJson,"hhh"))
                    .iii(parseFloatFunc(bffJson,"iii")).jjj(parseFloatFunc(bffJson,"jjj"))
                    .age(parseIntFunc(bffJson, "age")).job(parseIntFunc(bffJson, "job"))
                    .marriage(parseIntFunc(bffJson, "marriage")).edu(parseIntFunc(bffJson, "edu"))
                    .major(parseIntFunc(bffJson, "major")).religion(parseIntFunc(bffJson, "religion"))
                    .salary(parseIntFunc(bffJson, "salary")).vehicles(parseIntFunc(bffJson, "vehicles"))
                    .commt(parseIntFunc(bffJson, "commt")).travel(parseIntFunc(bffJson, "travel"))
                    .sns1(parseIntFunc(bffJson, "sns1")).sns2(parseIntFunc(bffJson, "sns2"))
                    .culture(parseIntFunc(bffJson, "culture")).build();
            bffs.add(bff);
        }
        bffRepository.saveAll(bffs);
    }

    public void saveBffDataByCsvFiles(List<MultipartFile> files, String basePath) throws IOException {
        for (MultipartFile file : files) {
            String filePath = basePath + "\\" + file.getOriginalFilename();
            File dest = new File(filePath);
            System.out.println(filePath);
            file.transferTo(dest); // 파일 업로드 작업 수행
            saveBffData(filePath);
        }
    }

    public ArrayList<BffResDto> getBffDataAll() {
        List<Bff> bffAll = bffRepository.findAll();
        ArrayList<BffResDto> bffResDtos = new ArrayList<>();
        for (Bff bff : bffAll) {
            bffResDtos.add(new BffResDto(bff));
        }
        return bffResDtos;
    }

    private Float parseFloatFunc(JSONObject bffJson, String key) {
        return Float.parseFloat(bffJson.get(key).toString());
    }
    private Integer parseIntFunc(JSONObject bffJson, String key) {
        return Integer.parseInt(bffJson.get(key).toString());
    }
}
