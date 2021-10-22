package pem.demo.domain.mobilityData.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pem.demo.domain.mobilityData.dao.MobilityData;

import java.util.Optional;

public interface MBRepository extends JpaRepository<MobilityData, Long> {

    @Query("select mb from MobilityData mb where mb.member.id = :memberId")
    public Page<MobilityData> findMobilityDataByMemberName(@Param("memberId") Long memberId, Pageable pageable);


    @Query("select mb from MobilityData mb where mb.ymd = :ymd")
    public MobilityData findMobilityDataByYmd(String ymd);


    public MobilityData findDistinctFirstByYmd(String ymd);

    public MobilityData findFirstByYmd(String ymd);

}
