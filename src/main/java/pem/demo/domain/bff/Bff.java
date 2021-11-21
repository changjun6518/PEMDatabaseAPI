package pem.demo.domain.bff;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import pem.demo.domain.member.Member;
import pem.demo.util.BaseTimeEntity;

import javax.persistence.*;
import java.util.ArrayList;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class Bff {
    @Id
    @GeneratedValue
    @Column(name = "bff_id")
    private Long id;

    private Float o;
    private Float c;
    private Float e;
    private Float a;
    private Float n;
    private Float aaa;
    private Float bbb;
    private Float ccc;
    private Float ddd;
    private Float eee;
    private Float fff;
    private Float ggg;
    private Float hhh;
    private Float iii;
    private Float jjj;

    private int age;
    private int job;
    private int marriage;
    private int edu;
    private int major;
    private int religion;
    private int salary;
    private int vehicles;
    private int commt;
    private int travel;
    private int sns1;
    private int sns2;
    private int culture;


}
