package com._thefull.dasom_web_demo.domain.user.repository;

import com._thefull.dasom_web_demo.domain.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
   // Optional<User> findByPhoneNum(String phoneNum);
    
  
	@Query("SELECT u FROM User u " +
		       "LEFT JOIN FETCH u.userStores us " +
		       "LEFT JOIN FETCH us.store s " +
		       "LEFT JOIN FETCH s.robot " +  // 필드명이 정확히 일치하는지 확인
		       "WHERE u.phoneNum = :phoneNum")
		Optional<User> findByPhoneNumWithStoresAndRobots(@Param("phoneNum") String phoneNum);



}
