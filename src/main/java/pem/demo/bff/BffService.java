package pem.demo.bff;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import pem.demo.bff.dto.BffResDto;
import pem.demo.member.Member;
import pem.demo.member.MemberService;

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
        //Charset.forName("UTF-8");
        String line = "";
        String firstLine = br.readLine();
        System.out.println(firstLine);
        while ((line = br.readLine()) != null) {
            ArrayList<Float> bffFeatures = new ArrayList<>();
            String array[] = line.split(",");
            String name = array[0];
            Member member = memberService.findUserByUserName(name);
            for (int i = 1; i < array.length; i++) {
                float bffFeature = Float.parseFloat(array[i]);
                bffFeatures.add(bffFeature);
            }
            bffRepository.save(new Bff(member, bffFeatures));
        }

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

    public BffResDto getBffData(String name) {
        Bff bffByMemberName = bffRepository.findBffByMemberName(name);
        return new BffResDto(bffByMemberName);
    }

    public ArrayList<BffResDto> getBffDataAll() {
        List<Bff> bffAll = bffRepository.findAll();
        ArrayList<BffResDto> bffResDtos = new ArrayList<>();
        for (Bff bff : bffAll) {
            bffResDtos.add(new BffResDto(bff));
        }
        return bffResDtos;
    }
}
