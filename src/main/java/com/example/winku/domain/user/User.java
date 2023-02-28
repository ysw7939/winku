package com.example.winku.domain.user;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

@Getter
@Setter
public class User {
    private String id;
    private String name;

    private String password;

    private String loginId;

    private String email;

    private String  gender;

    private String profileImg;

    public User() {
    }

    public User(String name, String password, String loginId, String email, String gender) {
        this.name = name;
        this.password = password;
        this.loginId = loginId;
        this.email = email;
        this.gender = gender;
    }
}
