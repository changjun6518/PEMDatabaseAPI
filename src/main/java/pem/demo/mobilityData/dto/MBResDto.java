package pem.demo.mobilityData.dto;

import lombok.Data;
import pem.demo.mobilityData.MobilityData;

@Data
public class MBResDto {
    private String name;
    private String ymd;
    private String hms;
    private String unixTime;
    private String latitude;
    private String longitude;

    public MBResDto(MobilityData mobilityData) {
        name = mobilityData.getMember().getName();
        ymd = mobilityData.getYmd();
        hms = mobilityData.getHms();
        unixTime = mobilityData.getUnixTime();
        latitude = mobilityData.getLatitude();
        longitude = mobilityData.getLongitude();
    }
}
