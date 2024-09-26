package com._thefull.dasom_web_demo.domain.dasomLocation.repository;


import com._thefull.dasom_web_demo.domain.dasomLocation.domain.DasomLocation;
import com._thefull.dasom_web_demo.domain.robot.domain.Robot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DasomLocationRepository extends JpaRepository<DasomLocation, Long> {

    List<DasomLocation> findAllByRobotOrderByUseDesc(Robot robot);
    
    // robotId와 use가 true인 첫 번째 DasomLocation을 조회
    Optional<DasomLocation> findFirstByRobotIdAndUseTrueOrderByUseDesc(int robotId);
    
}
