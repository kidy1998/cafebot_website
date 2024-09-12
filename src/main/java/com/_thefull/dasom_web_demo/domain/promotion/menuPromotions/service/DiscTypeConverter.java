package com._thefull.dasom_web_demo.domain.promotion.menuPromotions.service;


import com._thefull.dasom_web_demo.domain.promotion.menuPromotions.domain.DiscType;
import com._thefull.dasom_web_demo.global.exception.AppException;
import com._thefull.dasom_web_demo.global.exception.ErrorCode;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class DiscTypeConverter implements AttributeConverter<DiscType,Integer> {
    @Override
    public Integer convertToDatabaseColumn(DiscType discType) {
        if (discType==null)
            throw new AppException(ErrorCode.NO_DISC_TYPE,"존재하지 않는 할인 타입입니다");
        return discType.getIntVal();

    }

    @Override
    public DiscType convertToEntityAttribute(Integer intVal) {
        if(intVal==null)
            throw new AppException(ErrorCode.NO_DISC_TYPE,"존재하지 않는 할인 타입입니다");
        return DiscType.fromIntVal(intVal);
    }
}
