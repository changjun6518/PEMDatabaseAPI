package pem.demo.bff.dto;

import lombok.Data;
import pem.demo.bff.Bff;

@Data
public class BffResDto {
    private String name;
    private Float o;
    private Float c;
    private Float e;
    private Float a;
    private Float n;

    public BffResDto(Bff bff) {
        this.name = bff.getMember().getName();
        this.o = bff.getO();
        this.c = bff.getC();
        this.e = bff.getE();
        this.a = bff.getA();
        this.n = bff.getN();
    }
}
