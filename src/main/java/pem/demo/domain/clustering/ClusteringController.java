package pem.demo.domain.clustering;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ClusteringController {

    @Autowired
    private ClusteringRepository clusteringRepository;

}
