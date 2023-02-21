package com.example.winku.Service.comment;

import com.example.winku.Service.recomment.RecommentService;
import com.example.winku.domain.comment.Comment;
import com.example.winku.domain.comment.Recomment;
import com.example.winku.dto.comment.CreateCommentDto;
import com.example.winku.dto.comment.DeleteCommentDto;
import com.example.winku.dto.recomment.DeleteRecommentDto;
import com.example.winku.repository.comment.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {
    private final CommentRepository commentRepository;
    private final RecommentService recommentService;

    @Autowired
    public CommentServiceImpl(CommentRepository commentRepository, RecommentService recommentService) {
        this.commentRepository = commentRepository;
        this.recommentService = recommentService;
    }


    @Override
    public Comment saveComment(Comment comment) {
        return commentRepository.save(comment);
    }

    @Override
    public List<Comment> findAllbyFeedId(long feedId) {
        for (Comment comment : commentRepository.findAll()) {
            comment.setRecommentList(recommentService.findAllbyCommentId(comment.getId()));
        }
        return commentRepository.findAllbyFeedId(feedId);
    }

    @Override
    public Comment createComment(CreateCommentDto commentDto) {
        return commentRepository.createComment(commentDto);
    }

    @Override
    public void deleteComment(DeleteCommentDto commentDto) {
        for (Recomment recomment : recommentService.findAllbyCommentId(commentDto.getId())) {
            DeleteRecommentDto deleteRecommentDto = new DeleteRecommentDto(recomment.getId());
            recommentService.deleteRecomment(deleteRecommentDto);
        }

        commentRepository.deleteComment(commentDto);
    }


}
