package com._thefull.dasom_web_demo.domain.menu.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "TEMP_MENU_INGRED")
@Builder
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class TempMenuIngred {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 10)
    private String unit;

    private Integer quant;

    private Integer price;

    private String name;

    @ManyToOne
    @JoinColumn(name = "MENU_ID")
    private Menu menu;

}
