package pem.demo.mobility;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import pem.demo.domain.Member;
import pem.demo.util.BaseTimeEntity;

import javax.persistence.*;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MobilityData extends BaseTimeEntity {
    @Id
    @GeneratedValue
    @Column(name = "mobility_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "member_id")
    private Member member;

    private String ymd;
    private String hms;
    private String unix_time;
    private String latitude;
    private String longitude;

    public MobilityData(String longData) {
        String str[] =  longData.split("\t");
        ymd = str[0];
        hms = str[1];
        unix_time = str[2];
        latitude = str[3];
        longitude = str[4];
    }

    public MobilityData(Member user, String longData) {
        member =  user;

        String str[] =  longData.split("\t");
        ymd = str[0];
        hms = str[1];
        unix_time = str[2];
        latitude = str[3];
        longitude = str[4];
    }


    @Override
    public String toString() {
        return "MobilityData{" +
                "user='" + member.getName() + '\'' +
                ", ymd='" + ymd + '\'' +
                ", hms='" + hms + '\'' +
                ", unix_time='" + unix_time + '\'' +
                ", latitude='" + latitude + '\'' +
                ", longitude='" + longitude + '\'' +
                '}';
    }
}