package com.example.winku.dto.recomment;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter @Setter
public class CreateRecommentDto {
    private long commentId;
    @NotBlank
    private String content;
    private String userName;
    private String profile;

    public CreateRecommentDto() {
    }

    public CreateRecommentDto(long commentId, String content, String userName, String profile) {
        this.commentId = commentId;
        this.content = content;
        this.userName = userName;
        this.profile = profile;
    }
}
