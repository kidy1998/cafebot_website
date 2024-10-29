package com._thefull.dasom_web_demo.domain.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com._thefull.dasom_web_demo.domain.user.domain.UserStore;

public interface UserStoreRepository extends JpaRepository<UserStore,Long> {

}
