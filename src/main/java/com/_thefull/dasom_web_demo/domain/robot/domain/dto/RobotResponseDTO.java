package com._thefull.dasom_web_demo.domain.robot.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter @Builder
@AllArgsConstructor
public class RobotResponseDTO {
    private Long id;
    private String model;

}
