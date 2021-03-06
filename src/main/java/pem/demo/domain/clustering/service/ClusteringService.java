package pem.demo.domain.clustering.service;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import pem.demo.domain.clustering.dao.Clustering;
import pem.demo.domain.clustering.dao.ClusteringRepository;
import pem.demo.domain.clustering.dto.ClusteringDto;
import pem.demo.domain.clustering.exception.NotFoundUserException;
import pem.demo.domain.member.Member;
import pem.demo.domain.member.MemberRepository;
import pem.demo.util.FileUtil;
import pem.demo.util.constant.FilePathMessage;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ClusteringService {
    @Autowired
    private ClusteringRepository clusteringRepository;
    @Autowired
    private MemberRepository memberRepository;

    public void save(List<MultipartFile> files) throws IOException {
        for (MultipartFile file : files) {
            String filePath = FileUtil.getFilePathByIntegratedJsonFile(file);
            File dest = new File(filePath);
            file.transferTo(dest);
            toJsonAndSave(file);
        }
    }

    public List<ClusteringDto> findByUserName(String userName) throws NotFoundUserException {
        Optional<Member> member = memberRepository.findByName(userName);
        if (member.isPresent()) {
            List<Clustering> clusterings = member.get().getClusterings();
            return clusterings.stream().map(clustering -> ClusteringDto.of(clustering)).collect(Collectors.toList());
        }
        throw new NotFoundUserException();
    }

    private void toJsonAndSave(MultipartFile file) {
        JSONParser parser = new JSONParser();
        try {
            System.out.println("basePath + file.getOriginalFilename() = " + FileUtil.getFilePathByIntegratedJsonFile(file));
            FileReader reader = new FileReader(FileUtil.getFilePathByIntegratedJsonFile(file));
            Object obj = parser.parse(reader);
            JSONArray arr = (JSONArray) obj;
            reader.close();
            ArrayList<Clustering> clusterings = new ArrayList<>();

            for (Object object : arr.toArray()) {
                JSONObject cluster = (JSONObject) object;
                System.out.println("cluster = " + cluster);
                Clustering clustering = Clustering.builder()
                        .cluster((String) cluster.get("cluster"))
                        .latitude(Double.parseDouble((String) cluster.get("latitude")))
                        .longitude(Double.parseDouble((String) cluster.get("latitude")))
                        .maxDistance(Double.parseDouble((String) cluster.get("maxDistance")))
                        .meanDistance(Double.parseDouble((String) cluster.get("meanDistance")))
                        .timeRatio(Double.parseDouble((String) cluster.get("timeRatio")))
                        .hourSpent(Double.parseDouble((String) cluster.get("hourSpent")))
                        .count(Long.parseLong((String) cluster.get("count")))
                        .ROAVD(Double.parseDouble((String) cluster.get("ROAVD")))
                        .ROAVF(Double.parseDouble((String) cluster.get("ROAVF")))
                        .member(memberRepository.findByName((String) cluster.get("author")).get())
                        .build();
                clusterings.add(clustering);
            }

            clusteringRepository.saveAll(clusterings);

        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }

    }

    private void toJsonAndSave(String filePath) {
        JSONParser parser = new JSONParser();
        try {
            System.out.println("filePath : " + filePath);
            FileReader reader = new FileReader(filePath);
            Object obj = parser.parse(reader);
            JSONArray arr = (JSONArray) obj;
            reader.close();
            ArrayList<Clustering> clusterings = new ArrayList<>();

            for (Object object : arr.toArray()) {
                JSONObject cluster = (JSONObject) object;
                System.out.println("cluster = " + cluster);
                Clustering clustering = Clustering.builder()
                        .cluster((String) cluster.get("cluster"))
                        .latitude(Double.parseDouble((String) cluster.get("latitude")))
                        .longitude(Double.parseDouble((String) cluster.get("latitude")))
                        .maxDistance(Double.parseDouble((String) cluster.get("maxDistance")))
                        .meanDistance(Double.parseDouble((String) cluster.get("meanDistance")))
                        .timeRatio(Double.parseDouble((String) cluster.get("timeRatio")))
                        .hourSpent(Double.parseDouble((String) cluster.get("hourSpent")))
                        .count(Long.parseLong((String) cluster.get("count")))
                        .ROAVD(Double.parseDouble((String) cluster.get("ROAVD")))
                        .ROAVF(Double.parseDouble((String) cluster.get("ROAVF")))
                        .member(memberRepository.findByName((String) cluster.get("author")).get())
                        .build();
                clusterings.add(clustering);
            }

            clusteringRepository.saveAll(clusterings);

        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }

    }

    public void autoGetJsonFile() throws IOException {
//        String rootPath = FileSystemView.getFileSystemView().getHomeDirectory().toString();
//        String path = rootPath + "/pemDB/clusterPython/result/";
        String path = "/home/PEM/jenkins/workspace/devOps/clusterPython/result/";
        File dir = new File(path);
        String files[] = dir.list();

        for (String name : files) {
            String jsonFile = path + name + "/results/integratedJSON/Integrated_" + name + "_Clustering_Result.json";
            toJsonAndSave(jsonFile);
        }
    }
}
