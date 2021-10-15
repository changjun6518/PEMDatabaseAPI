package pem.demo.domain.member;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import pem.demo.domain.bff.Bff;
import pem.demo.domain.clustering.Clustering;
import pem.demo.domain.mobilityData.dao.MobilityData;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Member {
    @Id
    @GeneratedValue
    @Column(name = "member_id")
    private Long id;

    private String name;

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private final List<MobilityData> mobilityDatas = new ArrayList<>();

    @OneToOne(mappedBy = "member", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Bff bff;

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private final List<Clustering> clusterings = new ArrayList<>();


    public Member(String name) {
        this.name = name.toLowerCase();
    }


}
