package pem.demo.bff;

import lombok.Getter;
import lombok.NoArgsConstructor;
import pem.demo.member.Member;
import pem.demo.util.BaseTimeEntity;

import javax.persistence.*;
import java.util.ArrayList;

@Entity
@NoArgsConstructor
@Getter
public class Bff extends BaseTimeEntity {
    @Id
    @GeneratedValue
    @Column(name = "bff_id")
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;


    private Float o;
    private Float c;
    private Float e;
    private Float a;
    private Float n;

    public Bff(Member member, ArrayList<Float> bffFeatures) {
        this.member = member;
        this.o = bffFeatures.get(0);
        this.c = bffFeatures.get(1);
        this.e = bffFeatures.get(2);
        this.a = bffFeatures.get(3);
        this.n = bffFeatures.get(4);
    }
}
