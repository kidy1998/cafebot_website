package com._thefull.dasom_web_demo.domain.menu.repository;

import com._thefull.dasom_web_demo.domain.menu.domain.Menu;
import com._thefull.dasom_web_demo.domain.store.domain.Store;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MenuRepository extends JpaRepository<Menu, Long> {

    Optional<Menu> findFirstByName(String name);
    List<Menu> findByStoreOrderByCategoryAsc(Store store);
    List<Menu> findAllByStoreAndNameContainingOrderByCategoryAsc(Store store, String search);
	Optional<Menu> findByName(String string);
}
