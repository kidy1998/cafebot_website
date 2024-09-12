//package com._thefull.dasom_web_demo.domain.robot.service;
//
//import com._thefull.dasom_web_demo.global.exception.AppException;
//import com._thefull.dasom_web_demo.global.exception.ErrorCode;
//import com._thefull.dasom_web_demo.domain.robot.domain.Robot;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.stereotype.Service;
//
//@Service
//@Slf4j
//@RequiredArgsConstructor
//public class RobotService {
//    private final com._thefull.dasom_web_demo.domain.robot.repository.RobotRepository RobotRepository;
//    public Robot findRobot(Long robotId){
//        return RobotRepository.findById(robotId)
//                .orElseThrow(()->new AppException(ErrorCode.NOT_FOUND_STORE,"로봇명을 찾을 수 없습니다"));
//
//
//
//    }
//
//
//}
