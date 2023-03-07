package com.example.winku.dto.user;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;
@Getter @Setter
public class ProfileDto {
    MultipartFile profileImg;
    String userId;

    public ProfileDto() {
    }

    public ProfileDto(MultipartFile profileImg, String userId) {
        this.profileImg = profileImg;
        this.userId = userId;
    }
}

