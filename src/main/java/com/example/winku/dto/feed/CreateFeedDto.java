package com.example.winku.dto.feed;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class CreateFeedDto {
    private String name;
    private String profile;
    @NotBlank
    private String content;
    private String imgPath;


    public CreateFeedDto() {
    }

    public CreateFeedDto(String name, String profile, String content, String imgPath) {
        this.name = name;
        this.profile = profile;
        this.content = content;
        this.imgPath = imgPath;
    }
}
