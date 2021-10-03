package pem.demo.domain.clustering;

import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.Test;
import java.io.FileReader;
import java.io.IOException;

import org.json.simple.parser.JSONParser;

//@SpringBootTest
class ClusteringRepositoryTest {

//    @Autowired
//    ClusteringRepository clusteringRepository;

    @Test
    void test() throws IOException, ParseException {

        JSONParser jsonParser = new JSONParser();

        FileReader fileReader = new FileReader("C:\\\\Users\\\\ChangJun.Choi\\\\Desktop\\\\pemDB\\\\clusterPython\\\\result\\\\psy\\\\results\\\\integratedJSON\\\\" +
                "Integrated_psy_Clustering_Result.json");

        JSONObject jsonObject = (JSONObject) jsonParser.parse(fileReader);

        System.out.println(jsonObject);

        JSONObject asd = (JSONObject) jsonObject.get("a");
        System.out.println(asd.get("cluster"));

    }




}