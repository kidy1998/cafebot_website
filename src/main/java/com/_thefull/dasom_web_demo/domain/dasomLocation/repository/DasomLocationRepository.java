package com._thefull.dasom_web_demo.domain.dasomLocation.repository;


import com._thefull.dasom_web_demo.domain.dasomLocation.domain.DasomLocation;
import com._thefull.dasom_web_demo.domain.robot.domain.Robot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DasomLocationRepository extends JpaRepository<DasomLocation, Long> {

    List<DasomLocation> findAllByRobotOrderByUseDesc(Robot robot);
}
