package pem.demo.domain.clustering.api;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pem.demo.domain.clustering.dao.Clustering;
import pem.demo.domain.clustering.exception.NotFoundUserException;
import pem.demo.domain.clustering.service.ClusteringService;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/clustering")
public class ClusteringApi {

    private final ClusteringService clusteringService;

    public ClusteringApi(ClusteringService clusteringService) {
        this.clusteringService = clusteringService;
    }

    @GetMapping("getData")
    public List<Clustering> getData(@RequestParam("name") String name) throws NotFoundUserException {
        return clusteringService.findByUserName(name);
    }

    @GetMapping("self")
    public ResponseEntity<String> getLocalData(@RequestParam("name") String name) throws SQLException, IOException {
        clusteringService.autoGetJsonFile();
        return ResponseEntity.status(HttpStatus.OK).body("SaveSuccess");
    }
}
