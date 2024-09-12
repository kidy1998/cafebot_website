package com._thefull.dasom_web_demo.domain.promotion.menuPromotions.service;


import com._thefull.dasom_web_demo.domain.promotion.menuPromotions.domain.DateType;
import com._thefull.dasom_web_demo.global.exception.AppException;
import com._thefull.dasom_web_demo.global.exception.ErrorCode;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class DateTypeConverter implements AttributeConverter<DateType, Integer> {
    @Override
    public Integer convertToDatabaseColumn(DateType dateType) {
        if(dateType==null)
            throw new AppException(ErrorCode.NO_DISC_TYPE,"존재하지 않는 할인 종류입니다");
        return dateType.getIntVal();
    }

    @Override
    public DateType convertToEntityAttribute(Integer intVal) {
        if(intVal==null)
            throw new AppException(ErrorCode.NO_DISC_TYPE,"존재하지 않는 할인 종류입니다");
        return DateType.fromIntVal(intVal);
    }
}
