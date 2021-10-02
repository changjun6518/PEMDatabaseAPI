package pem.demo.domain.clustering;

import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
public class ClusteringPreference {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cluster_preference_id")
    private Long id;

    private String cluster;
    private Double latitude;
    private Double longitude;
    private Long count;
    private Double LVFIA;
    private Double ROAVF;
    private Double hourSpent;
    private Double LVDIA;
    private Double ROAVD;


}
