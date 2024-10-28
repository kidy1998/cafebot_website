package com._thefull.dasom_web_demo.domain.user.service;

import com._thefull.dasom_web_demo.domain.store.domain.Store;
import com._thefull.dasom_web_demo.domain.user.domain.dto.LoginRequestDto;
import com._thefull.dasom_web_demo.domain.user.domain.User;
import com._thefull.dasom_web_demo.domain.user.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class LoginService {
    private final UserRepository userRepository;

    public User login(LoginRequestDto dto) {
        Optional<User> optionalUser = userRepository.findByPhoneNumWithStoresAndRobots(dto.getPhoneNum());
       

        return optionalUser.orElse(null);  // Optional이 비어있다면 null 반환
       
    }

}