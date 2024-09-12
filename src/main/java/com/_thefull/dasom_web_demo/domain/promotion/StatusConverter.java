package com._thefull.dasom_web_demo.domain.promotion;

import com._thefull.dasom_web_demo.global.exception.AppException;
import com._thefull.dasom_web_demo.global.exception.ErrorCode;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class StatusConverter implements AttributeConverter<Status, Integer> {
    @Override
    public Integer convertToDatabaseColumn(Status status) {
        if (status ==null)
            throw new AppException(ErrorCode.INVALID_STATE,"확인되지 않은 상태입니다");

        return status.getStateNum();
    }

    @Override
    public Status convertToEntityAttribute(Integer stateNum) {
        if (stateNum==null)
            throw new AppException(ErrorCode.INVALID_STATE,"확인되지 않은 상태입니다");
        return Status.fromStateNum(stateNum);
    }
}
