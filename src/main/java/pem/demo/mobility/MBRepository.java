package pem.demo.mobility;

import org.springframework.data.jpa.repository.JpaRepository;
import pem.demo.mobility.MobilityData;

public interface MBRepository extends JpaRepository<MobilityData, Long> {

}
