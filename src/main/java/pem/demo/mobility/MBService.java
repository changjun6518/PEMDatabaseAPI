package pem.demo.mobility;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MBService {
    @Autowired
    MBRepository mbRepository;

    public void add(MobilityData mb) {
        mbRepository.save(mb);
    }

    public void saveDataByDir(String dirName) {
        GetDataByDir getDataByDir = new GetDataByDir();
        getDataByDir.run(dirName, mbRepository);
    }



}
