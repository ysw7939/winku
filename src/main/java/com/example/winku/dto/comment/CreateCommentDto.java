package com.example.winku.dto.comment;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class CreateCommentDto {
    private String userName;
    private String loginId;
    @NotBlank
    private String content;
    private Long feedId;
    private String profile;

    public CreateCommentDto(String userName,String loginId, String content, Long feedId, String profile) {
        this.userName = userName;
        this.loginId = loginId;
        this.content = content;
        this.feedId = feedId;
        this.profile = profile;
    }

}
