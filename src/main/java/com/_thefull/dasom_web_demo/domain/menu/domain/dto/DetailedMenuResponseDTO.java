package com._thefull.dasom_web_demo.domain.menu.domain.dto;

import com._thefull.dasom_web_demo.domain.menu.domain.Menu;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter @Builder
@AllArgsConstructor
public class DetailedMenuResponseDTO {
    private SimpleMenuResponseDTO menuDetail;
    private List<TempIngredResponseDTO> ingredList;

    public static DetailedMenuResponseDTO of(Menu e){
        return DetailedMenuResponseDTO.builder()
                .menuDetail(SimpleMenuResponseDTO.of(e))
                .ingredList(e.getIngredList().stream().map(TempIngredResponseDTO::of).collect(Collectors.toList()))
                .build();
    }

}
