package com._thefull.dasom_web_demo.domain.store.domain;

import com._thefull.dasom_web_demo.domain.menu.domain.Menu;
import com._thefull.dasom_web_demo.domain.promotion.menuPromotions.domain.MenuPromotion;
import com._thefull.dasom_web_demo.domain.robot.domain.Robot;
import com._thefull.dasom_web_demo.domain.user.domain.User;
import com._thefull.dasom_web_demo.domain.user.domain.UserStore;
import com._thefull.dasom_web_demo.global.domain.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;



/**
 * STORE 테이블에 연결된 엔티티 
 */
@Entity
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
@Builder @Getter
public class Store extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "STORE_ID")
    private Long id;

    @Column(name = "NAME", length = 100)
    private String name;

    @Column(name = "COMPANY_NAME",length = 50)
    private String companyName;

    @Column(name = "INSTAGRAM_LINK")
    private String instagramLink;

    @Column(name = "PHONE_NUM")
    private String phoneNum;

    @Column(name = "CODE")
    private String code;

//    @CreatedDate
//    @Column(name = "REGISTER_DATE", updatable = false, columnDefinition = "TIMESTAMP")
//    private LocalDateTime registerDate;
//
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "USER_ID")
//    private User user;

    @Builder.Default
    @OneToMany(mappedBy = "store", cascade = CascadeType.ALL)
    private List<Menu> menuList =new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "store", cascade = CascadeType.ALL)
    private List<Robot> robotList = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "store", cascade = CascadeType.ALL)
    private List<MenuPromotion> menuPromotionList = new ArrayList<>();

//    public void changeUser(User user){
//        this.user=user;
//    }
    
    // UserStore와의 관계 설정
    @OneToMany(mappedBy = "store", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<UserStore> userStores;
    
    @OneToOne(mappedBy = "store", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Robot robot;

}
