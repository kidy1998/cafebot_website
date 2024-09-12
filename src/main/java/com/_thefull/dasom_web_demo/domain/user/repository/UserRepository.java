package com._thefull.dasom_web_demo.domain.user.repository;

import com._thefull.dasom_web_demo.domain.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByPhoneNum(String phoneNum);
}
