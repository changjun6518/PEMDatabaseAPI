package pem.demo.domain.mobilityData.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import pem.demo.domain.member.Member;
import pem.demo.domain.member.MemberService;
import pem.demo.domain.mobilityData.dao.MBRepository;
import pem.demo.domain.mobilityData.dao.MobilityData;
import pem.demo.domain.mobilityData.dto.MBResDto;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

@Service
public class MBService{
    private final MBRepository mbRepository;
    private final MemberService memberService;

    public MBService(MemberService memberService, MBRepository mbRepository) {
        this.mbRepository = mbRepository;
        this.memberService = memberService;
    }

    public void add(MobilityData mb) {
        mbRepository.save(mb);
    }

    public void batchInsert(String filePath) {
        String[] dirNameSplit = filePath.split("_");
        String userName = dirNameSplit[dirNameSplit.length - 1].replace(".txt", "").toLowerCase();       //get user name (the last directory name)
        Member member = memberService.findUserByUserName(userName);
        ArrayList<MobilityData> mobilityDataList = new ArrayList<>();
        long start = System.currentTimeMillis();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                mobilityDataList.add(new MobilityData(member, line));
            }
            mbRepository.saveAll(mobilityDataList);

            long end = System.currentTimeMillis();
            System.out.printf("execution time : %f sec\n", (float) (end - start) / 1000L);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Page<MBResDto> getMBData(int offset, int limit, String name) {
        PageRequest pageRequest = PageRequest.of(offset, limit, Sort.Direction.ASC, "unixTime");
        Member userByUserName = memberService.findUserByUserName(name);
        Page<MobilityData> mobilityDataByMemberName = mbRepository.findMobilityDataByMemberName(userByUserName.getId(), pageRequest);
        return mobilityDataByMemberName.map(MBResDto::new);
    }

    public void deleteMBData(String ymd) {
        List<MobilityData> mbDataList = mbRepository.findAllByYmd(ymd);
        mbRepository.deleteAll(mbDataList);
    }

}
