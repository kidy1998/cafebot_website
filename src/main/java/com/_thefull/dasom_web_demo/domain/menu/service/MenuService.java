package com._thefull.dasom_web_demo.domain.menu.service;


import com._thefull.dasom_web_demo.domain.menu.domain.Menu;
import com._thefull.dasom_web_demo.domain.menu.domain.dto.DetailedMenuResponseDTO;
import com._thefull.dasom_web_demo.domain.menu.domain.dto.SimpleMenuResponseDTO;
import com._thefull.dasom_web_demo.domain.menu.repository.MenuRepository;
import com._thefull.dasom_web_demo.domain.store.domain.Store;
import com._thefull.dasom_web_demo.domain.store.repository.StoreRepository;
import com._thefull.dasom_web_demo.global.exception.AppException;
import com._thefull.dasom_web_demo.global.exception.ErrorCode;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class MenuService {

    private final MenuRepository menuRepository;
    private final StoreRepository storeRepository;

    @Transactional
    public List<Menu> findAllMenu(Long storeId){
        Store store = storeRepository.findById(storeId)
                .orElseThrow(() -> new AppException(ErrorCode.NOT_FOUND_STORE, "매장을 찾을 수 없습니다"));
        return menuRepository.findByStoreOrderByCategoryAsc(store);
    }

    public List<SimpleMenuResponseDTO> findSearchedMenu(Long storeId, String search) {

        Store store = storeRepository.findById(storeId)
                .orElseThrow(() -> new AppException(ErrorCode.NOT_FOUND_STORE, "매장을 찾을 수 없습니다"));

        List<Menu> menuList = menuRepository.findAllByStoreAndNameContainingOrderByCategoryAsc(store, search);

        return menuList.stream().map(SimpleMenuResponseDTO::of).collect(Collectors.toList());

    }

    public DetailedMenuResponseDTO findOneMenuDetails(Long storeId, Long menuId){
        Store store = storeRepository.findById(storeId)
                .orElseThrow(() -> new AppException(ErrorCode.NOT_FOUND_STORE, "매장을 찾을 수 없습니다"));
        Menu menu = menuRepository.findById(menuId)
                .orElseThrow(() -> new AppException(ErrorCode.NOT_FOUND_MENU, "메뉴를 찾을 수 없습니다"));

        if (menu.getStore().getId()!=storeId)
            throw new AppException(ErrorCode.UNAUTHORIZED_USER, "매장에 존재하지 않는 메뉴입니다");

        return DetailedMenuResponseDTO.of(menu);

    }
}
