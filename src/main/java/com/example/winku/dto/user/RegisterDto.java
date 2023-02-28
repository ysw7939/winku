package com.example.winku.dto.user;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class RegisterDto {
    @NotBlank
    private String name;
    @NotBlank
    private String LoginId;
    @NotBlank
    private String password;
    @NotBlank
    private String gender;
    @NotBlank
    private String email;


    public RegisterDto() {
    }

    public RegisterDto(String name, String loginId, String password, String gender, String email) {
        this.name = name;
        this.LoginId = loginId;
        this.password = password;
        this.gender = gender;
        this.email = email;
    }
}
