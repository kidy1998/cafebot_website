package com._thefull.dasom_web_demo.domain.store.service;

import com._thefull.dasom_web_demo.domain.store.domain.Store;
import com._thefull.dasom_web_demo.domain.store.repository.StoreRepository;
import com._thefull.dasom_web_demo.global.exception.AppException;
import com._thefull.dasom_web_demo.global.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class StoreService {
    private final StoreRepository storeRepository;
    public Store findStore(Long storeId){
        return storeRepository.findById(storeId)
                .orElseThrow(()->new AppException(ErrorCode.NOT_FOUND_STORE,"매장을 찾을 수 없습니다"));

    }
}
