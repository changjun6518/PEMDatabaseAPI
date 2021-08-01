package pem.demo.mobilityData;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import pem.demo.member.Member;
import pem.demo.util.BaseTimeEntity;

import javax.persistence.*;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class MobilityData extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "member_id")
    private Member member;

    private String ymd;
    private String hms;
    private String unixTime;
    private String latitude;
    private String longitude;

    public MobilityData(String longData) {
        String str[] =  longData.split("\t");
        ymd = str[0];
        hms = str[1];
        unixTime = str[2];
        latitude = str[3];
        longitude = str[4];
    }

    public MobilityData(Member user, String longData) {
        member =  user;

        String str[] =  longData.split("\t");
        ymd = str[0];
        hms = str[1];
        unixTime = str[2];
        latitude = str[3];
        longitude = str[4];
    }


    @Override
    public String toString() {
        return "MobilityData{" +
                "user='" + member.getName() + '\'' +
                ", ymd='" + ymd + '\'' +
                ", hms='" + hms + '\'' +
                ", unix_time='" + unixTime + '\'' +
                ", latitude='" + latitude + '\'' +
                ", longitude='" + longitude + '\'' +
                '}';
    }
}
