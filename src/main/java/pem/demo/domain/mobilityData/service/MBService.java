package pem.demo.domain.mobilityData.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import pem.demo.domain.member.Member;
import pem.demo.domain.member.MemberService;
import pem.demo.domain.mobilityData.GetDataByFile;
import pem.demo.domain.mobilityData.dao.MBRepository;
import pem.demo.domain.mobilityData.dao.MobilityData;
import pem.demo.domain.mobilityData.dto.MBResDto;

@Service
@RequiredArgsConstructor
public class MBService {
    @Autowired
    MBRepository mbRepository;
    @Autowired
    MemberService memberService;


    public void add(MobilityData mb) {
        mbRepository.save(mb);
    }


    public void saveDataByFile(String filePath) {
        GetDataByFile getDataByFile = new GetDataByFile(mbRepository, memberService);
        getDataByFile.run(filePath);
    }

    public Page<MBResDto> getMBData(int offset, int limit, String name) {
        PageRequest pageRequest = PageRequest.of(offset, limit, Sort.Direction.ASC, "unixTime");
        Member userByUserName = memberService.findUserByUserName(name);
        Page<MobilityData> mobilityDataByMemberName = mbRepository.findMobilityDataByMemberName(userByUserName.getId(), pageRequest);
        return mobilityDataByMemberName.map(MBResDto::new);
    }
}
