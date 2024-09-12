package com._thefull.dasom_web_demo.domain.menu.domain.dto;

import com._thefull.dasom_web_demo.domain.menu.domain.TempMenuIngred;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class TempIngredResponseDTO
{
    private String name;
    private Integer price;
    private Integer quant;
    private String unit;

    public static TempIngredResponseDTO of(TempMenuIngred e){
        return TempIngredResponseDTO.builder()
                .name(e.getName())
                .price(e.getPrice())
                .quant(e.getQuant())
                .unit(e.getUnit())
                .build();
    }

}
