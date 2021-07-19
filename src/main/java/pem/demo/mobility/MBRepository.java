package pem.demo.mobility;

import org.springframework.data.jpa.repository.JpaRepository;
import pem.demo.domain.MobilityData;

public interface MBRepository extends JpaRepository<MobilityData, Long> {

}
