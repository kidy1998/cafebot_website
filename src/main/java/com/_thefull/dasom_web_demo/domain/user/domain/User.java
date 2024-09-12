package com._thefull.dasom_web_demo.domain.user.domain;

/*import com._thefull.dasom_web_demo.domain.robot.domain.Robot;*/
import com._thefull.dasom_web_demo.domain.store.domain.Store;
import com._thefull.dasom_web_demo.global.domain.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.validator.constraints.UniqueElements;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity(name = "USER")
@EntityListeners(AuditingEntityListener.class)
@Builder
public class User extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "USER_ID")
    private Long userId;

    @Column(length = 50, name = "NAME")
    private String name;

    @Column(length = 20, name = "PHONE_NUM", unique = true)
    private String phoneNum;

    @Column(name = "PASSWORD")
    private String password;

//    @Column(name = "EMAIL")
//    private String email;

    @Column(name = "PROFILE_IMG_URL")
    private String profileImageUrl;

    @CreatedDate
    @Column(columnDefinition = "TIMESTAMP", name = "REGISTER_DATE", updatable = false)
    private LocalDateTime registerAt;

    // BUILDER 패턴은 초기화를 해주지 않는다. 따라서 직접 선언+초기화까지 한 경우에는 @Builder.Default를 하라고 lombok에서 exception으로 알려줌.
    @Builder.Default
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Store> storeList = new ArrayList<>();
}
