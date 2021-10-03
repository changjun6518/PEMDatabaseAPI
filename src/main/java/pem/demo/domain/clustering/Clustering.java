package pem.demo.domain.clustering;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Clustering {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cluster_id")
    private Long id;

    private String cluster;
    private Double latitude;
    private Double longitude;
    private Double maxDistance;
    private Double meanDistance;
    private Double timeRatio;
    private Double hourSpent;
    private Long count;
    private Double ROAVD;
    private Double ROAVF;


}
