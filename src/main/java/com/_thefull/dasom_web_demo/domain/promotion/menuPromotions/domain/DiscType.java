package com._thefull.dasom_web_demo.domain.promotion.menuPromotions.domain;


import com._thefull.dasom_web_demo.global.exception.AppException;
import com._thefull.dasom_web_demo.global.exception.ErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum DiscType {

    PERCENT(1,"% 할인"),
    PRICE(2,"가격 할인"),
    NONE(0, "없음");

    private final int intVal;
    private final String koreanName;

    public static DiscType fromIntVal(int intVal){
        for(DiscType discType: DiscType.values()){
            if(discType.intVal==intVal)
                return discType;
        }
        throw new AppException(ErrorCode.NO_DISC_TYPE,"존재하지 않는 할인 종류입니다");
    }

    public static DiscType fromName(String name){
        for(DiscType discType: DiscType.values()){
            if(discType.name().equals(name))
                return discType;
        }
        throw new AppException(ErrorCode.NO_DISC_TYPE,"존재하지 않는 할인 종류입니다");
    }
}

