package com._thefull.dasom_web_demo.domain.promotion;

import com._thefull.dasom_web_demo.global.exception.AppException;
import com._thefull.dasom_web_demo.global.exception.ErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Status {
    IN_PROGRESS(1, "진행중"),
    SCHEDULED(2,"예정"),
    STOPPED(3,"중지"),
    COMPLETED(4,"완료");

    private final int stateNum;
    private final String stateType;

    public static Status fromStateNum(int stateNum){
        for (Status s: Status.values()){
            if (s.getStateNum()==stateNum)
                return s;
        }
        throw new AppException(ErrorCode.INVALID_STATE,"확인되지 않은 상태입니다");
    }

    public static Status fromStateType(String stateType){
        for (Status s: Status.values()){
            if(s.getStateType().equals(stateType)){
                return s;
            }
        }
        throw new AppException(ErrorCode.INVALID_STATE,"확인되지 않은 상태입니다");
    }
}
