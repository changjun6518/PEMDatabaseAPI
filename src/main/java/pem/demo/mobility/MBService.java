package pem.demo.mobility;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MBService {
    @Autowired
    MBRepository mbRepository;

    public void add(MobilityData mb) {
        mbRepository.save(mb);
    }

    public void saveDataByFile(String dirName) {
        GetDataByFile getDataByFile = new GetDataByFile();
        getDataByFile.run(dirName, mbRepository);
    }



}
