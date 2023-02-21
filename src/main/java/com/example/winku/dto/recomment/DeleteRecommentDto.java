package com.example.winku.dto.recomment;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class DeleteRecommentDto {
    private Long id;

    public DeleteRecommentDto(Long id) {
        this.id = id;
    }
}
