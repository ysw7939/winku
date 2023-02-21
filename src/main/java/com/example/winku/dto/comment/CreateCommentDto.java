package com.example.winku.dto.comment;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class CreateCommentDto {
    private String userName;
    @NotBlank
    private String content;
    private Long feedId;
    private String profile;

    public CreateCommentDto(String userName, String content, Long feedId, String profile) {
        this.userName = userName;
        this.content = content;
        this.feedId = feedId;
        this.profile = profile;
    }

}
