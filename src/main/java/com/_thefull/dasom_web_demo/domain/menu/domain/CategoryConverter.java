package com._thefull.dasom_web_demo.domain.menu.domain;

import com._thefull.dasom_web_demo.global.exception.AppException;
import com._thefull.dasom_web_demo.global.exception.ErrorCode;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class CategoryConverter implements AttributeConverter<Category, Integer> {

    @Override
    public Integer convertToDatabaseColumn(Category category) {
        if (category==null)
            throw new AppException(ErrorCode.NO_CATEGORY,"존재하지 않는 카테고리입니다");

        return category.getCategoryNum();
    }

    @Override
    public Category convertToEntityAttribute(Integer categoryNum) {
        if (categoryNum==null)
            throw new AppException(ErrorCode.NO_CATEGORY,"존재하지 않는 카테고리입니다");
        return Category.fromCategoryNum(categoryNum);
    }
}
