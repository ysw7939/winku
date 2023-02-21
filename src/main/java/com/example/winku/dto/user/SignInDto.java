package com.example.winku.dto.user;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignInDto {
    @NotBlank
    private String loginId;
    @NotBlank
    private String password;

    public SignInDto() {
    }

    public SignInDto(String loginId, String password) {
        this.loginId = loginId;
        this.password = password;
    }
}
