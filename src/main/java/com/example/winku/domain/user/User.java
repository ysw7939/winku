package com.example.winku.domain.user;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

@Getter
@Setter
public class User {
    private String id;
    @NotBlank
    private String name;
    @NotBlank
    @Length(min = 8)
    private String password;
    @NotBlank
    private String loginId;
    @NotBlank
    private String email;
    @NotBlank
    private String  gender;

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
