package com._thefull.dasom_web_demo.domain.dasomLocation.domain;

import com._thefull.dasom_web_demo.domain.dasomLocation.domain.dto.DasomLocationRequestDTO;
import com._thefull.dasom_web_demo.domain.dasomLocation.service.StringListConverter;
import com._thefull.dasom_web_demo.domain.robot.domain.Robot;
import com._thefull.dasom_web_demo.global.domain.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;


/**
 * DASOM_LOCATION 연결된 엔티티
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
@Entity(name = "DASOM_LOCATION")
public class DasomLocation extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ROBOT_LOCATION_ID",updatable = false)
    private Long id;

    @Column(name = "LOCATION")
    private String location;

    @Column(name = "LEFT_SIDE")
    @Convert(converter = StringListConverter.class)
    private List<String> leftSide;

    @Column(name = "LEFT_FRONT")
    @Convert(converter = StringListConverter.class)
    private List<String> leftFront;

    @Column(name = "FRONT")
    @Convert(converter = StringListConverter.class)
    private List<String> front;

    @Column(name = "RIGHT_SIDE")
    @Convert(converter = StringListConverter.class)
    private List<String> rightSide;

    @Column(name = "RIGHT_FRONT")
    @Convert(converter = StringListConverter.class)
    private List<String> rightFront;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ROBOT_ID")
    private Robot robot;  // Robot과의 관계 정의

    @Column(name = "`USE`")
    @Builder.Default
    private Boolean use=false;

    public void changeUse(Boolean use){
        this.use=use;
    }

    public void update(DasomLocationRequestDTO dto) {
        this.leftSide=dto.getLeftSide();
        this.rightSide=dto.getRightSide();
        this.leftFront=dto.getLeftFront();
        this.rightFront=dto.getRightFront();
        this.location= dto.getLocation();
        this.front = dto.getFront();

    }
}
