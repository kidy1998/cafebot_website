package com._thefull.dasom_web_demo.domain.promotion.menuPromotions.domain.dto;

import lombok.Data;
import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class MenuPromotionRequestDTO {
    private Long menuPromoId;

    private String category;
    private String menu;

    private Integer price;		// 원래 가격
    private Integer discVal = 0;	// 얼마나 할인하는지
    private Integer discPrice;  // 할인 적용된 가격

    private LocalDate startDate;
    private LocalDate endDate;


    private Boolean boolIsAlways;
    private LocalTime startTime;
    private LocalTime endTime;
    private Boolean boolEqlStoreOpr = false;

    private LocalTime mentStartTime;
    private LocalTime mentEndTime;
    private Boolean boolEqlEventStart = false;

    private Integer interval;

    private Boolean boolAddCond;
    private String addDiscCond;

    private Boolean boolAddDesc;
    private String addMenuDesc;

    private String ment;

}
