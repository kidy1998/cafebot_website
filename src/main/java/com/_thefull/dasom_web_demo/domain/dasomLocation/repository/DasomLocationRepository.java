package com._thefull.dasom_web_demo.domain.dasomLocation.repository;


import com._thefull.dasom_web_demo.domain.dasomLocation.domain.DasomLocation;
import com._thefull.dasom_web_demo.domain.robot.domain.Robot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface DasomLocationRepository extends JpaRepository<DasomLocation, Long> {

    List<DasomLocation> findAllByRobotOrderByUseDesc(Robot robot);
    
    // robotId와 use가 true인 첫 번째 DasomLocation을 조회
    Optional<DasomLocation> findFirstByRobotIdAndUseTrueOrderByUseDesc(int robotId);
    
    // robotId로 use 값을 일괄 업데이트하는 메서드 추가
    @Transactional
    @Modifying
    @Query("UPDATE DASOM_LOCATION d SET d.use = false WHERE d.robot.id = :robotId")
    void updateUseByRobotId(@Param("robotId") Long robotId);
}
