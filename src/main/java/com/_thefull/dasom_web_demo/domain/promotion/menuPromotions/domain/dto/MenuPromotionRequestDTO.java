package com._thefull.dasom_web_demo.domain.promotion.menuPromotions.domain.dto;

import lombok.Data;
import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class MenuPromotionRequestDTO {
    private Long menuPromoId;

    private String category;
    private String menu;

    private Integer price;
    private Integer discVal;
    private Integer discPrice;

    private LocalDate startDate;
    private LocalDate endDate;


    private Boolean boolIsAlways;
    private LocalTime startTime;
    private LocalTime endTime;
    private Boolean boolEqlStoreOpr;

    private LocalTime mentStartTime;
    private LocalTime mentEndTime;
    private Boolean boolEqlEventStart;

    private Integer interval;

    private Boolean boolAddCond;
    private String addDiscCond;

    private Boolean boolAddDesc;
    private String addMenuDesc;

    private String ment;

}
