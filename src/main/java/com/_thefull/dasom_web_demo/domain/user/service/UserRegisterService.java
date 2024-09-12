package com._thefull.dasom_web_demo.domain.user.service;

import com._thefull.dasom_web_demo.domain.robot.domain.Robot;
import com._thefull.dasom_web_demo.domain.robot.repository.RobotRepository;
import com._thefull.dasom_web_demo.domain.store.domain.Store;
import com._thefull.dasom_web_demo.domain.store.repository.StoreRepository;
import com._thefull.dasom_web_demo.domain.user.repository.UserRepository;
import com._thefull.dasom_web_demo.domain.user.domain.User;
import com._thefull.dasom_web_demo.domain.user.domain.dto.UserJoinRequestDto;
import com._thefull.dasom_web_demo.global.exception.AppException;
import com._thefull.dasom_web_demo.global.exception.ErrorCode;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserRegisterService {

    private final UserRepository userRepository;
    private final StoreRepository storeRepository;
    private final RobotRepository robotRepository;

    @Transactional
    public void registerUser(UserJoinRequestDto dto){
        User newUser = dto.toEntity();
        User savedUser = userRepository.save(newUser);

        Store findStore = storeRepository.findByCode(dto.getCode())
                .orElseThrow(() -> new AppException(ErrorCode.NOT_FOUND_STORE, "매장을 찾지 못했습니다"));

        findStore.changeUser(savedUser);

        Robot newRobot = Robot.builder().model("DASOM1004").store(findStore).build();
        robotRepository.save(newRobot);


    }


}
