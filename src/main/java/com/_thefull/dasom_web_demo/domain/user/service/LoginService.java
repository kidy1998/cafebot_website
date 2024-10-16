package com._thefull.dasom_web_demo.domain.user.service;

import com._thefull.dasom_web_demo.domain.store.domain.Store;
import com._thefull.dasom_web_demo.domain.user.domain.dto.LoginRequestDto;
import com._thefull.dasom_web_demo.domain.user.domain.User;
import com._thefull.dasom_web_demo.domain.user.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class LoginService {
    private final UserRepository userRepository;

    public User login(LoginRequestDto dto) {
        Optional<User> optionalUser = userRepository.findByPhoneNum(dto.getPhoneNum());
        
     // Optional 객체에서 값이 있는지 확인한 후 처리
        if (optionalUser.isPresent()) {
            // 값이 존재할 때만 get()을 호출
            User user = optionalUser.get();
            return user;
        } else {
            // Optional이 비어있다면 null을 반환
            return null;
        }
       
    }

}