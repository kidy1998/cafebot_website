package com._thefull.dasom_web_demo.domain.promotion.menuPromotions.domain.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class ForMentOfMenuPromotionDTO {
    private String menu;
    private Integer price;
    private String discType;
    private Integer discVal;
    private String dateType;
    private LocalDate startDate;
    private LocalDate endDate;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm")
    private LocalTime startTime;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm")
    private LocalTime endTime;
    private Boolean isAddDiscCond;
    private String addDiscCond;
    private Boolean isAddMenuDesc;
    private String addMenuDesc;
}
