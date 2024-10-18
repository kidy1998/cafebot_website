package com._thefull.dasom_web_demo.domain.promotion.menuPromotions.domain;


import com._thefull.dasom_web_demo.domain.menu.domain.Category;
import com._thefull.dasom_web_demo.domain.menu.domain.CategoryConverter;
import com._thefull.dasom_web_demo.domain.menu.domain.Menu;
import com._thefull.dasom_web_demo.domain.promotion.Status;
import com._thefull.dasom_web_demo.domain.promotion.StatusConverter;
import com._thefull.dasom_web_demo.domain.promotion.menuPromotions.domain.dto.MenuPromotionRequestDTO;
import com._thefull.dasom_web_demo.domain.promotion.menuPromotions.service.DateTypeConverter;
import com._thefull.dasom_web_demo.domain.promotion.menuPromotions.service.DiscTypeConverter;
import com._thefull.dasom_web_demo.domain.store.domain.Store;
import com._thefull.dasom_web_demo.global.domain.BaseEntity;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.validator.internal.util.stereotypes.ThreadSafe;

import java.sql.Time;
import java.time.*;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;
import java.util.Date;



@Entity
@Table(name = "MENU_PROMOTIONS")
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MenuPromotion extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MENU_PROMO_ID", updatable = false)
    private Long id;

    // 프로모션 사용 여부
    @Column(name = "`USE`")
    private Boolean use;

    // 카테고리
    @Setter
    @Convert(converter = CategoryConverter.class)
    @Column(name = "category" , columnDefinition = "TINYINT")
    private Category category;

    // 상태
    @Convert(converter = StatusConverter.class)
    @Column(name = "STATUS" , columnDefinition = "TINYINT")
    private Status status;

//    //정가
//    @Column(name = "PRICE")
//    @NotNull
//    private int price;

//    // 할인 적용 가격 : 할인이 적용된 가격 ex) 5000원 -> 4000원
//    @Column(name = "DISC_PRICE")
//    private int discPrice;

    @Convert(converter = DiscTypeConverter.class)
    @Column(name = "DISC_TYPE", columnDefinition = "TINYINT")
    private DiscType discType;

    // 할인값 or 할인율
    @Builder.Default
    @Column(name = "DISC_VAL")
    private Integer discVal=0;

    @Convert(converter = DateTypeConverter.class)
    @Column(name = "DATE_TYPE", columnDefinition = "TINYINT")
    private DateType dateType;

    // 프로모션 시작일
    @Column(name = "START_DATE")
    private LocalDate startDate;

    // 프로모션 종료일
    @Column(name = "END_DATE")
    private LocalDate endDate;

    // 영업시간과 동일 여부
    @Column(name = "IS_EQL_OPR_TIME")
    private Boolean isEqlOprTime;

    // 프로모션 시작 시간
    @Column(name = "START_TIME")
    private LocalTime startTime;

    // 프로모션 종료 시간
    @Column(name = "END_TIME")
    private LocalTime endTime;

    // 행사시간과 동일 여부
    @Column(name = "IS_EQL_PROMO_TIME")
    private Boolean isEqlPromoTime;

    // 멘트 발화 시간
    @Column(name = "MENT_START_TIME")
    private LocalTime mentStartTime;

    // 멘트 발화 종료 시간
    @Column(name = "MENT_END_TIME")
    private LocalTime mentEndTime;

    // 발화 빈도수
    @Column(name = "MENT_INTERVAL")
    private Integer mentInterval;

    // 프로모션 소개 추가 여부
    @Column(name = "IS_ADD_DESC")
    private Boolean isAddMenuDesc;

    // 프로모션 추가 소개
    @Column(name = "ADD_DESC", columnDefinition = "TINYTEXT")
    private String addMenuDesc;

    // 할인 조건 추가 여부
    @Column(name = "IS_ADD_COND")
    private Boolean isAddDiscCond;

    // 할인 조건 추가한 내용
    @Column(name = "ADD_DISC_COND", length = 1000)
    private String addDiscCond;

    // 다솜 멘트
    @Column(name = "MENT", columnDefinition = "TEXT")
    private String ment;

    // 매장
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "STORE_ID")
    private Store store;

    // 메뉴
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MENU_ID")
    private Menu menu;

    public void updateStatus(Status status){
        this.status=status;
    }

    public void updateMenuPromotion(MenuPromotionRequestDTO dto, Menu menu,Status status){
        LocalDate startDate, endDate;
        String discType, dateType;


        if(dto.getBoolIsAlways()==true) {
            dateType="ALWAYS";
            startDate = LocalDate.now();
            endDate= startDate.with(TemporalAdjusters.lastDayOfYear());

        }else if (ChronoUnit.DAYS.between(dto.getStartDate(), dto.getEndDate()) == 7){
            dateType="ONE_WEEK";
            startDate=dto.getStartDate();
            endDate=dto.getEndDate();

        }else if (ChronoUnit.DAYS.between(dto.getStartDate(), dto.getEndDate()) ==14){
            dateType="TWO_WEEKS";
            startDate=dto.getStartDate();
            endDate=dto.getEndDate();

        }else{
            dateType="OTHER";
            startDate=dto.getStartDate();
            endDate=dto.getEndDate();

        }

        this.category=menu.getCategory();
        this.status=status;
        this.discType=DiscType.fromName("PRICE");
        this.discVal=dto.getDiscVal();
        this.dateType= DateType.fromName(dateType);
        this.startDate=startDate;
        this.endDate=endDate;
        this.isEqlOprTime=dto.getBoolEqlStoreOpr();
        this.startTime = dto.getStartTime();
        this.endTime=dto.getEndTime();
        this.isEqlPromoTime=dto.getBoolEqlEventStart();
        this.mentStartTime=dto.getMentStartTime();
        this.mentEndTime=dto.getMentEndTime();
        this.mentInterval=Math.abs(dto.getInterval());
        this.isAddDiscCond=dto.getBoolAddCond();
        this.addDiscCond=dto.getAddDiscCond();
        this.isAddMenuDesc=dto.getBoolAddDesc();
        this.addMenuDesc=dto.getAddMenuDesc();
        this.ment= dto.getMent();
        this.menu=menu;
        //this.price = dto.getPrice();

    }

    public static MenuPromotion from(MenuPromotionRequestDTO dto, Menu menu, Status status, Store store){

        LocalDate startDate, endDate;
        String discType, dateType;

        if(dto.getBoolIsAlways() == null) {
        	dto.setBoolIsAlways(false);
        }
        

        if(dto.getBoolIsAlways()==true) {
            dateType="ALWAYS";
            startDate = LocalDate.now();
            endDate= startDate.with(TemporalAdjusters.lastDayOfYear());

        }else if (ChronoUnit.DAYS.between(dto.getStartDate(), dto.getEndDate()) == 7){
            dateType="ONE_WEEK";
            startDate=dto.getStartDate();
            endDate=dto.getEndDate();

        }else if (ChronoUnit.DAYS.between(dto.getStartDate(), dto.getEndDate()) ==14){
            dateType="TWO_WEEKS";
            startDate=dto.getStartDate();
            endDate=dto.getEndDate();

        }else{
            dateType="OTHER";
            startDate=dto.getStartDate();
            endDate=dto.getEndDate();

        }


        return MenuPromotion.builder()
                .use(true)
                .menu(menu)
                .category(menu.getCategory())
                .status(status)
                .discType(DiscType.fromName("PRICE"))
                .discVal(dto.getDiscVal())
                .dateType(DateType.fromName(dateType))
                .startDate(startDate)
                .endDate(endDate)
                .isEqlOprTime(dto.getBoolEqlStoreOpr())
                .startTime(dto.getStartTime())
                .endTime(dto.getEndTime())
                .isEqlPromoTime(dto.getBoolEqlEventStart())
                .mentStartTime(dto.getMentStartTime())
                .mentEndTime(dto.getMentEndTime())
                .mentInterval(dto.getInterval())
                .isAddDiscCond(dto.getBoolAddCond())
                .addDiscCond(dto.getAddDiscCond())
                .addMenuDesc(dto.getAddMenuDesc())
                .isAddMenuDesc(dto.getBoolAddDesc())
                .ment(dto.getMent())
                .store(store)
                .build();

    }

}



//
//@Entity
//@Table(name = "MENU_PROMOTIONS")
//@Getter @SuperBuilder
//@NoArgsConstructor
//@AllArgsConstructor
//public class MenuPromotion extends BasePromotionEntity {
//
//    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "MENU_PROMO_ID", updatable = false)
//    private Long id;
//
//    //할인 여부 : 할인하지 않은 상품도 홍보할 수 있습니다.
//    @Column(name = "IS_DISC")
//    private Boolean isDisc;
//
//    @Column(name = "PRICE")
//    @NotNull
//    private Integer price;
//
//    // 할인 적용 가격 : 할인이 적용된 가격
//    @Column(name = "DISC_PRICE")
//    private Integer discPrice;
//
//    // 할인값(원)
//    @Builder.Default
//    @Column(name = "DISC_VAL")
//    private Integer discVal=0;
//
//    // 할인율(%)
//    @Builder.Default
//    @Column(name = "DISC_RATE")
//    private Integer discRate=0;
//
//    @Transient
//    private Boolean isDiscRate;
//
//    @Column(name = "IS_ADD_COND")
//    private Boolean isAddCond;
//
//    @Column(name = "ADD_DISC_COND", length = 1000)
//    private String addDiscCond;
//
//    @Column(name = "IS_ALWAYS")
//    private Boolean isAlways;
//
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "STORE_ID")
//    private Store store;
//
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "MENU_ID")
//    private Menu menu;
//
//    @PostLoad
//    public void setIsDiscRate(){
//
//        if (this.discRate==0)
//            this.isDiscRate=false;
//        else
//            this.isDiscRate=true;
//    }
//
//
//    public void updateEntity(MenuPromotionRequestDTO dto,int freq){
//        super.updateEntity(dto,freq);
//        this.price=dto.getPrice();
//        this.discVal=dto.getDiscVal();
//        this.discPrice= dto.getDiscPrice();
//        this.isAddCond=dto.getBoolAddCond();
//        this.addDiscCond=dto.getAddDiscCond();
//        this.isAlways=dto.getBoolIsAlways();
//
//    }
//
//
//    public static MenuPromotion from(MenuPromotionRequestDTO dto, Menu menu, Store store, Status status){
//        System.out.println(dto.getBoolAddDesc());
//        System.out.println(dto.getBoolAddCond());
//
//        return MenuPromotion.builder()
//                .status(status)
//                .menu(menu)
//                .use(true)
//                .price(dto.getPrice())
//                .discVal(dto.getDiscVal())
//                .discPrice(Math.abs(dto.getDiscPrice()))
//                .startDate(dto.getStartDate())
//                .endDate(dto.getEndDate())
//                .isAlways(dto.getBoolIsAlways())
//                .startTime(dto.getStartTime())
//                .endTime(dto.getEndTime())
//                .boolEqlStoreOpr(dto.getBoolEqlStoreOpr())
//                .mentStartTime(dto.getMentStartTime())
//                .mentEndTime(dto.getMentEndTime())
//                .boolEqlStoreOpr(dto.getBoolEqlStoreOpr())
//                .mentInterval(dto.getInterval())
//                .isAddCond(dto.getBoolAddCond())
//                .addDiscCond(dto.getAddDiscCond())
//                .isAddDesc(dto.getBoolAddDesc())
//                .addDesc(dto.getAddMenuDesc())
//                .ment(dto.getMent())
//                .store(store)
//                .build();
//    }
//
//
//
//}
