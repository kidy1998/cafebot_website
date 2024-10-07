package com._thefull.dasom_web_demo.domain.dasomLocation.service;

import com._thefull.dasom_web_demo.domain.dasomLocation.domain.DasomLocation;
import com._thefull.dasom_web_demo.domain.dasomLocation.domain.dto.DasomLocationRequestDTO;
import com._thefull.dasom_web_demo.domain.dasomLocation.domain.dto.DasomLocationResponseDTO;
import com._thefull.dasom_web_demo.domain.dasomLocation.repository.DasomLocationRepository;
import com._thefull.dasom_web_demo.domain.robot.repository.RobotRepository;
import com._thefull.dasom_web_demo.domain.store.domain.Store;
import com._thefull.dasom_web_demo.domain.store.repository.StoreRepository;
import com._thefull.dasom_web_demo.global.exception.AppException;
import com._thefull.dasom_web_demo.global.exception.ErrorCode;
import com._thefull.dasom_web_demo.domain.robot.domain.Robot;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j @Transactional
@RequiredArgsConstructor
public class DasomLocationService {

    private final DasomLocationRepository dasomLocationRepository;
    private final RobotRepository robotRepository;
    private final StoreRepository storeRepository;


    public List<DasomLocationResponseDTO> findAllRobotLocation(Long robotId, Long storeId) {
        Store store = storeRepository.findById(storeId)
                .orElseThrow(() -> new AppException(ErrorCode.NOT_FOUND_STORE, "매장을 찾을 수 없습니다."));
        Robot robot = robotRepository.findById(robotId)
                .orElseThrow(() -> new AppException(ErrorCode.NOT_FOUND_ROBOT, "카페봇 로봇을 찾을 수 없습니다"));

        if (!robot.getStore().getId().equals(store.getId()))
            throw new AppException(ErrorCode.UNAUTHORIZED_USER, "해당 카페봇이 현재 매장의 기기가 아니므로 조회할 수 없습니다.");

        List<DasomLocation> locationList = dasomLocationRepository.findAllByRobotOrderByUseDesc(robot);

        return locationList.stream().map(DasomLocationResponseDTO::of).collect(Collectors.toList());

    }

    public void createDasomLocation(DasomLocationRequestDTO requestDTO, Long storeId, Long robotId) {
        Store store = storeRepository.findById(storeId)
                .orElseThrow(() -> new AppException(ErrorCode.NOT_FOUND_STORE, "매장을 찾을 수 없습니다."));
        Robot robot = robotRepository.findById(robotId)
                .orElseThrow(() -> new AppException(ErrorCode.NOT_FOUND_ROBOT, "카페봇 로봇을 찾을 수 없습니다"));

        
        if (!robot.getStore().getId().equals(store.getId()))
            throw new AppException(ErrorCode.UNAUTHORIZED_USER, "해당 카페봇이 현재 매장의 기기가 아니므로 카페봇 위치 설정을 등록할 수 없습니다.");

        DasomLocation newLocation = requestDTO.from(robot);

        dasomLocationRepository.save(newLocation);
    }

    public void changeUse(Long robotId, Boolean use, Long locationId) {
        Robot robot = robotRepository.findById(robotId)
                .orElseThrow(() -> new AppException(ErrorCode.NOT_FOUND_ROBOT, "카페봇 로봇을 찾을 수 없습니다"));

        DasomLocation dasomLocation = dasomLocationRepository.findById(locationId)
                .orElseThrow(() -> new AppException(ErrorCode.NOT_FOUND_DASOM_LOCATION, "다솜 위치 설정 값을 찾을 수 없습니다"));

        /* 카페봇 상태 변경 권한 확인 - 해당 로봇에 대한 위치 설정이 맞는지 확인 */
        if(!dasomLocation.getRobot().getId().equals(robot.getId()))
            throw new AppException(ErrorCode.UNAUTHORIZED_USER, "해당 카페봇에 대한 현 위치 설정에 대한 권한이 없어 상태를 변경할 수 없습니다");

        dasomLocationRepository.updateUseByRobotId(robotId);
        
        dasomLocation.changeUse(use);

        dasomLocationRepository.save(dasomLocation);

    }

    public void deleteDasomLocation(Long robotId, Long locationId) {
        Robot robot = robotRepository.findById(robotId)
                .orElseThrow(() -> new AppException(ErrorCode.NOT_FOUND_ROBOT, "카페봇 로봇을 찾을 수 없습니다"));

        DasomLocation dasomLocation = dasomLocationRepository.findById(locationId)
                .orElseThrow(() -> new AppException(ErrorCode.NOT_FOUND_DASOM_LOCATION, "다솜 위치 설정 값을 찾을 수 없습니다"));

        /* 카페봇 위치 설정 삭제 권한 확인 - 해당 로봇에 대한 위치 설정이 맞는지 확인 */
        if (!dasomLocation.getRobot().getId().equals(robot.getId()))
            throw new AppException(ErrorCode.UNAUTHORIZED_USER, "해당 카페봇에 대한 현 위치 설정에 대한 권한이 없어 상태를 변경할 수 없습니다");

        dasomLocationRepository.deleteById(locationId);

    }

    public DasomLocationResponseDTO findLocationDetails(Long id) {
        DasomLocation dasomLocation = dasomLocationRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.NOT_FOUND_DASOM_LOCATION, "다솜 위치 설정 값을 찾을 수 없습니다"));

        return DasomLocationResponseDTO.of(dasomLocation);

    }

    public void updateLocation(DasomLocationRequestDTO dto, Long locationId, Long storeId) {
        DasomLocation dasomLocation = dasomLocationRepository.findById(locationId)
                .orElseThrow(() -> new AppException(ErrorCode.NOT_FOUND_DASOM_LOCATION, "다솜 위치 설정 값을 찾을 수 없습니다"));

        if (!dasomLocation.getRobot().getStore().getId().equals(storeId))
            throw new AppException(ErrorCode.UNAUTHORIZED_USER, "해당 카페봇에 대한 현 위치 설정에 대한 권한이 없어 내용을 변경할 수 없습니다");
        
        dasomLocation.update(dto);
       
        dasomLocationRepository.save(dasomLocation);
    }
    
    public DasomLocationResponseDTO findFirstRobotLocation(int robotId) {
        // robotId와 use가 true인 첫 번째 DasomLocation을 조회
        DasomLocation location = dasomLocationRepository.findFirstByRobotIdAndUseTrueOrderByUseDesc(robotId)
                .orElseThrow(() -> new AppException(ErrorCode.NOT_FOUND_DASOM_LOCATION, "해당 로봇의 위치를 찾을 수 없습니다."));

        // 결과를 DTO로 변환하여 반환
        return DasomLocationResponseDTO.of(location);
    }

}
