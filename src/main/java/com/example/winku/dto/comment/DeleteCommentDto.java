package com.example.winku.dto.comment;


import lombok.Getter;
import lombok.Setter;



@Getter @Setter
public class DeleteCommentDto {
    private Long id;

    public DeleteCommentDto() {
    }

    public DeleteCommentDto(Long id) {
        this.id = id;

    }
}
