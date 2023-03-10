package com.example.winku.dto.feed;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
public class CreateFeedDto {
    private String loginId;
    private String name;
    private String profile;
    @NotBlank
    private String content;
    private MultipartFile imgPath;

    public CreateFeedDto() {
    }

    public CreateFeedDto(String loginId, String name, String profile, String content, MultipartFile imgPath) {
        this.loginId = loginId;
        this.name = name;
        this.profile = profile;
        this.content = content;
        this.imgPath = imgPath;
    }
}
