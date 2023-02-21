package com.example.winku.repository.comment;

import com.example.winku.domain.comment.Comment;
import com.example.winku.dto.comment.CreateCommentDto;
import com.example.winku.dto.comment.DeleteCommentDto;

import java.util.List;

public interface CommentRepository {
    Comment save(Comment comment);
    List<Comment> findAll();
    List<Comment> findAllbyFeedId(long feedId);

    Comment createComment(CreateCommentDto commentDto);

    void deleteComment(DeleteCommentDto commentDto);
}
