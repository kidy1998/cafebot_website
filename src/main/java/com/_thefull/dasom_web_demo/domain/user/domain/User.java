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


/**
 * USER 테이블에 연결된 엔티티 
 */
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Setter
@Entity
@Table(name = "USER")
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
    
    //User 엔티티가 로드될 때, userStores 필드가 초기화되고, UserStore 객체를 통해 간접적으로 Store와 Robot 객체가 자동으로 매핑됩니다.
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<UserStore> userStores;

    // Store 목록 가져오기 편의를 위한 메서드 추가
    public List<Store> getStores() {
        return userStores.stream().map(UserStore::getStore).toList();
    }
    
//    User 객체 생성 
//    └── userStores 리스트 초기화 및 UserStore 객체 생성
//          └── UserStore 객체에서 Store 객체 생성
//                 └── Store 객체에서 Robot 객체 생성
    
    
    
    @Override
    public String toString() {
        return "User{" +
               "userId=" + userId +
               ", name='" + name + '\'' +
               ", phoneNum='" + phoneNum + '\'' +
               ", password='" + password + '\'' +
               ", profileImageUrl='" + profileImageUrl + '\'' +
               ", registerAt=" + registerAt +
               '}';
    }


//    // BUILDER 패턴은 초기화를 해주지 않는다. 따라서 직접 선언+초기화까지 한 경우에는 @Builder.Default를 하라고 lombok에서 exception으로 알려줌.
//    @Builder.Default
//    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
//    private List<Store> storeList = new ArrayList<>();
    
    
}
