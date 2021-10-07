package pem.demo.domain.clustering;

import org.json.JSONException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.json.simple.parser.JSONParser;

//@SpringBootTest
class ClusteringRepositoryTest {

    @Test
    @Disabled
    void 테스() throws IOException, ParseException {
        JSONParser parser = new JSONParser();
        try {
            FileReader reader = new FileReader("C:/Users/ChangJun.Choi/Desktop/pemDB/clusterPython/result/psy/results/integratedJSON/"
                    + "Integrated_psy_Clustering_Result.json");
            Object obj = parser.parse(reader);
            JSONArray arr = (JSONArray) obj;

            reader.close();

            for (int i = 0; i < arr.size(); i++) {
                JSONObject tmp = (JSONObject) arr.get(i);

//                System.out.println("인덱스 값 : " + i);
//                System.out.println("cluster : " + tmp.get("cluster"));
//                System.out.println("latitude : " + tmp.get("latitude"));
//                System.out.println("longitude : " + tmp.get("longitude"));
            }

            Object[] objects = arr.toArray();
            int i = 0;
            for (Object object : objects) {
                System.out.println("objects = " + i++ + " " + object);

            }

        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }

    }
}