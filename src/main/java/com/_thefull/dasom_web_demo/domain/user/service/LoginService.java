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

        // store와 phoneNum에 일치하는 User가 없으면 null return
        if (optionalUser.isEmpty()) {
            return null;
        }

        User user = optionalUser.get();
        // 찾아온 User의 password와 입력된 password가 다르면 null return
        if (!user.getPassword().equals(dto.getPassword())) {
            return null;
        }

        return user;
    }

}