package com._thefull.dasom_web_demo.domain.menu.domain;


import com._thefull.dasom_web_demo.global.exception.AppException;
import com._thefull.dasom_web_demo.global.exception.ErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Category {

    BEVERAGE(1,"BEVERAGE","BEVERAGE"),
    BAKERY(2, "BAKERY", "BAKERY"),
    CAKE(3, "CAKE", "CAKE"),
    SET(4, "SET","SET"),
    ALL(5, "ALL","ALL");


    private final int categoryNum;
    private final String categoryName;
    private final String simpleName;


    public static Category fromCategoryNum(int categoryNum){
        for (Category c: Category.values()){
            if (c.getCategoryNum()==categoryNum)
                return c;
        }
        throw new AppException(ErrorCode.NO_CATEGORY,"존재하지 않는 카테고리입니다");
    }

    public static Category fromCategoryName(String categoryName){
        for (Category c: Category.values()){
            if(c.name().equals(categoryName))
                return c;


        }
        throw new AppException(ErrorCode.NO_CATEGORY,"존재하지 않는 카테고리입니다");
    }
}