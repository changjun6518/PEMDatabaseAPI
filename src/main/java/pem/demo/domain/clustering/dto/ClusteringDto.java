package pem.demo.domain.clustering.dto;

import lombok.Builder;
import lombok.Data;
import pem.demo.domain.clustering.dao.Clustering;

@Data
public class ClusteringDto {
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

    public ClusteringDto(String cluster, Double latitude, Double longitude, Double maxDistance, Double meanDistance, Double timeRatio, Double hourSpent, Long count, Double ROAVD, Double ROAVF) {
        this.cluster = cluster;
        this.latitude = latitude;
        this.longitude = longitude;
        this.maxDistance = maxDistance;
        this.meanDistance = meanDistance;
        this.timeRatio = timeRatio;
        this.hourSpent = hourSpent;
        this.count = count;
        this.ROAVD = ROAVD;
        this.ROAVF = ROAVF;
    }

    public static ClusteringDto of(Clustering clustering) {
        return new ClusteringDto(clustering.getCluster(), clustering.getLatitude(), clustering.getLongitude(), clustering.getMaxDistance(),
                clustering.getMeanDistance(), clustering.getTimeRatio(), clustering.getHourSpent(),
                clustering.getCount(), clustering.getROAVD(), clustering.getROAVF());
    }
}
