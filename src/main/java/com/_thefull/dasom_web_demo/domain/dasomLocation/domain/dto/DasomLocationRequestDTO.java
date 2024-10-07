package com._thefull.dasom_web_demo.domain.dasomLocation.domain.dto;

import com._thefull.dasom_web_demo.domain.dasomLocation.domain.DasomLocation;
import com._thefull.dasom_web_demo.domain.robot.domain.Robot;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class DasomLocationRequestDTO {

    private Long id;

    @NotBlank
    private String location;

    private List<String> leftSide;
    private List<String> rightSide;
    private List<String> leftFront;
    private List<String> rightFront;
    private List<String> front;

    public DasomLocation from(Robot robot){
        return DasomLocation.builder()
                .location(this.location)
                .leftSide(this.leftSide)
                .rightSide(this.rightSide)
                .leftFront(this.leftFront)
                .rightFront(this.rightFront)
                .front(this.front)
                .robot(robot)
                .use(false)
                .build();
    }

}
