package com.example.winku.Service.comment;

import com.example.winku.domain.comment.Comment;
import com.example.winku.dto.comment.CreateCommentDto;
import com.example.winku.dto.comment.DeleteCommentDto;

import java.util.List;

public interface CommentService {
    Comment saveComment(Comment comment);
    List<Comment> findAllbyFeedId(long feedId);

    Comment createComment(CreateCommentDto commentDto);
    void deleteComment(DeleteCommentDto commentDto);
}
