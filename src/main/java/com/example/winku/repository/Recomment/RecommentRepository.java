package com.example.winku.repository.Recomment;

import com.example.winku.domain.comment.Recomment;
import com.example.winku.dto.comment.DeleteCommentDto;
import com.example.winku.dto.recomment.CreateRecommentDto;
import com.example.winku.dto.recomment.DeleteRecommentDto;

import java.util.List;

public interface RecommentRepository {
    Recomment save(Recomment recomment);

    List<Recomment> findAllbyCommentId(long commentId);

    Recomment createRecomment(CreateRecommentDto recommentDto);

    void deleteRecomment(DeleteRecommentDto recommentDto);

    Recomment recommentId(long recommentId);

}
