package pem.demo.domain.bff.dto;

import lombok.Data;
import pem.demo.domain.bff.Bff;

@Data
public class BffResDto {
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

    public BffResDto(Bff bff) {
        this.o = bff.getO();
        this.c = bff.getC();
        this.e = bff.getE();
        this.a = bff.getA();
        this.n = bff.getN();
        this.aaa = bff.getAaa();
        this.bbb = bff.getBbb();
        this.ccc = bff.getCcc();
        this.ddd = bff.getDdd();
        this.eee = bff.getEee();
        this.fff = bff.getFff();
        this.ggg = bff.getGgg();
        this.hhh = bff.getHhh();
        this.iii = bff.getIii();
        this.jjj = bff.getJjj();

        this.age = bff.getAge();
        this.job = bff.getJob();
        this.marriage = bff.getMarriage();
        this.edu = bff.getEdu();
        this.major = bff.getMajor();
        this.religion = bff.getReligion();
        this.salary = bff.getSalary();
        this.vehicles = bff.getVehicles();
        this.commt = bff.getCommt();
        this.travel = bff.getTravel();
        this.sns1 = bff.getSns1();
        this.sns2 = bff.getSns2();
        this.culture = bff.getCulture();
    }
}
