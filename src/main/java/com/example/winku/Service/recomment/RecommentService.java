package com.example.winku.Service.recomment;

import com.example.winku.domain.comment.Recomment;
import com.example.winku.dto.recomment.CreateRecommentDto;
import com.example.winku.dto.recomment.DeleteRecommentDto;

import java.util.List;

public interface RecommentService {
    Recomment saveRecomment(Recomment recomment);

    List<Recomment> findAllbyCommentId(Long commentId);

    Recomment createRecomment(CreateRecommentDto recommentDto);

    void deleteRecommentList(List<Recomment> recommentList);

    void deleteRecomment(DeleteRecommentDto recommentDto);
}
