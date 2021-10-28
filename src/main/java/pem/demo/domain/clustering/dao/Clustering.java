package pem.demo.domain.clustering.dao;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import pem.demo.domain.member.Member;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Clustering {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cluster_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

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
