package com._thefull.dasom_web_demo.domain.menu.domain.dto;

import com._thefull.dasom_web_demo.domain.menu.domain.Menu;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter @Builder
@AllArgsConstructor
public class SimpleMenuResponseDTO {

    private Long id;

    private String name;
    private Integer price;
    private Integer basePrice;
    private String imgUrl;
    private String desc;


    public static SimpleMenuResponseDTO of(Menu e){
        return SimpleMenuResponseDTO.builder()
            .id(e.getId())
            .name(e.getName())
            .price(e.getPrice())
            .basePrice(e.getBasePrice())
            .imgUrl(e.getImgUrl())
            .desc(e.getDesc())
            .build();
    }
}
