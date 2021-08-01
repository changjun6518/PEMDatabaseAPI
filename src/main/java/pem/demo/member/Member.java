package pem.demo.member;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import pem.demo.mobilityData.MobilityData;

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

    @OneToMany(mappedBy = "member")
    private List<MobilityData> mobilityDatas = new ArrayList<>();


    public Member(String name) {
        this.name = name.toLowerCase();
    }


}
