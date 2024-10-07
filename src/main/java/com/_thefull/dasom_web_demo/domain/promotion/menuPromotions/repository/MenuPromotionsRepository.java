package com._thefull.dasom_web_demo.domain.promotion.menuPromotions.repository;

import com._thefull.dasom_web_demo.domain.promotion.Status;
import com._thefull.dasom_web_demo.domain.promotion.menuPromotions.domain.MenuPromotion;
import com._thefull.dasom_web_demo.domain.store.domain.Store;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MenuPromotionsRepository extends JpaRepository<MenuPromotion, Long> {
    List<MenuPromotion> findByStore(Store store);
	List<MenuPromotion> findByStoreIdAndStatus(Long storeId, Status inProgress);
	List<MenuPromotion> findByStoreId(Long id);
}
