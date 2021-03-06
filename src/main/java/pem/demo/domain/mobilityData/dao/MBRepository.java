package pem.demo.domain.mobilityData.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pem.demo.domain.mobilityData.dao.MobilityData;

import java.util.List;
import java.util.Optional;

public interface MBRepository extends JpaRepository<MobilityData, Long> {

    @Query("select mb from MobilityData mb where mb.member.id = :memberId")
    public Page<MobilityData> findMobilityDataByMemberName(@Param("memberId") Long memberId, Pageable pageable);

    public MobilityData findFirstByYmd(String ymd);

    public List<MobilityData> findAllByYmd(String ymd);
}
