package com._thefull.dasom_web_demo.domain.promotion.menuPromotions.service;

import com._thefull.dasom_web_demo.domain.menu.domain.Menu;
import com._thefull.dasom_web_demo.domain.menu.repository.MenuRepository;
import com._thefull.dasom_web_demo.domain.promotion.Status;
import com._thefull.dasom_web_demo.domain.promotion.menuPromotions.domain.MenuPromotion;
import com._thefull.dasom_web_demo.domain.promotion.menuPromotions.domain.dto.MenuPromotionRequestDTO;
import com._thefull.dasom_web_demo.domain.promotion.menuPromotions.domain.dto.MenuPromotionResponseDTO;
import com._thefull.dasom_web_demo.domain.promotion.menuPromotions.repository.MenuPromotionsRepository;
import com._thefull.dasom_web_demo.domain.store.domain.Store;
import com._thefull.dasom_web_demo.domain.store.repository.StoreRepository;
import com._thefull.dasom_web_demo.global.exception.AppException;
import com._thefull.dasom_web_demo.global.exception.ErrorCode;
import jakarta.persistence.PrePersist;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Service;

import java.sql.Time;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class MenuPromotionService {

    private final MenuPromotionsRepository menuPromotionsRepository;
    private final StoreRepository storeRepository;
    private final MenuRepository menuRepository;

    @Transactional
    public List<MenuPromotionResponseDTO> findAllPromotionList(Long storeId) {
        Store store = storeRepository.findById(storeId)
                .orElseThrow(() -> new AppException(ErrorCode.NOT_FOUND_STORE, "매장을 찾을 수 없습니다"));

        List<MenuPromotionResponseDTO> responseDTOList = new ArrayList<>();
        List<MenuPromotion> findPromoList = menuPromotionsRepository.findByStore(store);
        for (MenuPromotion mp : findPromoList){
            if (mp.getStatus()!= Status.COMPLETED) {
                int freq= calculateFreq(mp.getMentInterval(), mp.getMentEndTime(), mp.getMentStartTime());
                MenuPromotionResponseDTO e = MenuPromotionResponseDTO.from(mp, freq);
                responseDTOList.add(e);
            }
        }
        return responseDTOList;
    }

    @Transactional
    public void registerMenuPromotion(Long storeId, MenuPromotionRequestDTO dto) {
        Store store = storeRepository.findById(storeId)
                .orElseThrow(() -> new AppException(ErrorCode.NOT_FOUND_STORE, "매장을 찾을 수 없습니다"));
        Menu menu = menuRepository.findByName(dto.getMenu())
                .orElseThrow(()-> new AppException(ErrorCode.NOT_FOUND_MENU, "메뉴를 찾을 수 없습니다"));
        

        Status status = decideStatus(dto.getEndDate(), dto.getStartDate(), dto.getBoolIsAlways());
        
        
        MenuPromotion newEntity = MenuPromotion.from(dto, menu, status, store);

        menuPromotionsRepository.save(newEntity);

    }

    @Transactional
    public List<MenuPromotionResponseDTO> findCompletedPromotionList(Long storeId) {
        Store store = storeRepository.findById(storeId)
                .orElseThrow(() -> new AppException(ErrorCode.NOT_FOUND_STORE, "매장을 찾을 수 없습니다"));

        List<MenuPromotionResponseDTO> responseDTOList = new ArrayList<>();
        List<MenuPromotion> findPromoList = menuPromotionsRepository.findByStore(store);
        for (MenuPromotion mp : findPromoList){
            if (mp.getStatus() == Status.COMPLETED) {
                int freq = calculateFreq(mp.getMentInterval(), mp.getMentEndTime(), mp.getMentStartTime());
                MenuPromotionResponseDTO e = MenuPromotionResponseDTO.from(mp,freq);
                responseDTOList.add(e);
            }
        }
        return responseDTOList;
    }

    @Transactional
    public MenuPromotionResponseDTO findOneMenuPromotion(Long id) {

        MenuPromotion findPromotion = menuPromotionsRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.NOT_FOUND_MENU_PROMOTION, "해당 제품할인을 찾지 못했습니다"));

        int freq = calculateFreq(findPromotion.getMentInterval(), findPromotion.getMentEndTime(), findPromotion.getMentStartTime());
        return MenuPromotionResponseDTO.from(findPromotion,freq);

    }

    public void updatePromotionContent(MenuPromotionRequestDTO dto) {
        MenuPromotion foundMenuPromotion = menuPromotionsRepository.findById(dto.getMenuPromoId())
                .orElseThrow(() -> new AppException(ErrorCode.NOT_FOUND_MENU_PROMOTION, "메뉴 프로모션을 찾을 수 없습니다"));

        Menu menu = menuRepository.findByName(dto.getMenu())
                .orElseThrow(()-> new AppException(ErrorCode.NOT_FOUND_MENU, "메뉴를 찾을 수 없습니다"));


        Status status = decideStatus(dto.getEndDate(), dto.getStartDate(), dto.getBoolIsAlways());
        int freq = calculateFreq(dto.getInterval(), dto.getMentEndTime(), dto.getMentStartTime());
        foundMenuPromotion.updateMenuPromotion(dto, menu, status);

        menuPromotionsRepository.save(foundMenuPromotion);

    }

    public void deleteMenuPromotion(Long id) {

        menuPromotionsRepository.deleteById(id);
    }

    @Transactional
    public void changeMenuPromotionStatus(Long id, String statusname) {

        MenuPromotion findPromotion = menuPromotionsRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.NOT_FOUND_MENU_PROMOTION, "해당 제품할인을 찾지 못했습니다"));

        Status status = Status.fromStateType(statusname);

        findPromotion.updateStatus(status);

        MenuPromotion save = menuPromotionsRepository.save(findPromotion);
        System.out.println(save.getStatus().getStateType());

    }

    private int calculateFreq(int interval, LocalTime mentEndTime, LocalTime mentStartTime){
        Duration duration;
        Long minutes = Duration.between(mentStartTime,mentEndTime).toMinutes();
        return Math.abs((int)(minutes/interval));
    }

    private Status decideStatus(LocalDate endDate, LocalDate startDate, Boolean isAlways){
        LocalDate now = LocalDate.now();
        
        if (isAlways){
            return Status.IN_PROGRESS; // 상시 상태로 설정
        }
        
        if (now.isAfter(endDate)) return Status.COMPLETED;
        else if(now.isBefore(startDate)) return Status.SCHEDULED;
        else return Status.IN_PROGRESS;
    }
    
    
    public String checkMent(LocalTime localTime) {
        
  
        // STATUS가 1(진행중)인 데이터 조회
        List<MenuPromotion> activePromotions = menuPromotionsRepository.findByStatus(Status.IN_PROGRESS);

        // STATUS가 1(진행중)인 데이터 중 mentStartTime과 mentEndTime 사이에 있는 경우 ment 반환
        for (MenuPromotion promotion : activePromotions) {
            if (localTime.isAfter(promotion.getMentStartTime()) && localTime.isBefore(promotion.getMentEndTime())) {
                return promotion.getMent();
            }
        }

        // 해당하는 경우가 없으면 빈 문자열 반환
        return "";
    }

    
    
    
}
