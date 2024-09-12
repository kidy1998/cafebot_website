package com._thefull.dasom_web_demo.domain.user.domain.dto;

import com._thefull.dasom_web_demo.domain.user.domain.User;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
public class UserJoinRequestDto {

    private String code;

    private String name;

    @Email
    private String email;

    private String phoneNum;

    @Size(min = 6)
    private String password;

    public User toEntity(){
        return User.builder()
                .name(this.name)
                .phoneNum(this.phoneNum)
                .password(this.password)
                .build();
    }

}
