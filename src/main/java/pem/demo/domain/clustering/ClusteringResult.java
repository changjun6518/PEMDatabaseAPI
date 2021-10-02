package pem.demo.domain.clustering;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
public class ClusteringResult {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cluster_result_id")
    private Long id;

    private String cluster;
    private String author;
    private Double latitude;
    private Double longitude;
    private Double maxDistance;
    private Double meanDistance;
    private Double timeRatio;
    private Double hourSpent;
    private Long count;
    private Double ROAVD;
    private Double ROAVF;

    public ClusteringResult(String cluster, String author, Double latitude, Double longitude, Double maxDistance,
                            Double meanDistance, Double timeRatio, Double hourSpent, Long count, Double ROAVD, Double ROAVF) {
        this.cluster = cluster;
        this.author = author;
        this.latitude = latitude;
        this.longitude = longitude;
        this.maxDistance = maxDistance;
        this.meanDistance = meanDistance;
        this.timeRatio = timeRatio;
        this.hourSpent = hourSpent;
        this.count = count;
        this.ROAVD = ROAVD;
        this.ROAVF = ROAVF;
    }
}
