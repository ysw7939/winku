package com.example.winku.controller.comment;

import com.example.winku.Service.comment.CommentServiceImpl;
import com.example.winku.Service.recomment.RecommentServiceImpl;
import com.example.winku.domain.user.User;
import com.example.winku.dto.comment.CreateCommentDto;
import com.example.winku.dto.comment.DeleteCommentDto;
import com.example.winku.dto.recomment.CreateRecommentDto;
import com.example.winku.dto.recomment.DeleteRecommentDto;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.server.ResponseStatusException;

@Controller
public class CommentController {
    final CommentServiceImpl commentService;
    final RecommentServiceImpl recommentService;

    public CommentController(CommentServiceImpl commentService, RecommentServiceImpl recommentService) {
        this.commentService = commentService;
        this.recommentService = recommentService;
    }
    @PostMapping("/feed/writeComment")
    public String commentWrite(@ModelAttribute CreateCommentDto commentDto, @RequestParam String redirect) {
        commentService.createComment(commentDto);
        return "redirect:" + redirect;
    }

    @PostMapping("/feed/writeRecomment")
    public String RecommentWrite(@ModelAttribute CreateRecommentDto recommentDto, @RequestParam String redirect) {
        recommentService.createRecomment(recommentDto);
        return "redirect:" + redirect;
    }

    @PostMapping("/feed/deleteComment")
    public String CommentDelete(@ModelAttribute DeleteCommentDto commentDto, @SessionAttribute(name = "user") User user, @RequestParam String redirect) {
        if (!commentService.findbyCommentId(commentDto.getId()).getLoginId().equals(user.getLoginId())) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN);
        }
        commentService.deleteComment(commentDto);
        return "redirect:" + redirect;
    }

    @PostMapping("/feed/deleteRecomment")
    public String RecommentDelete(@ModelAttribute DeleteRecommentDto recommentDto, @SessionAttribute(name = "user") User user, @RequestParam String redirect) {
        if (!recommentService.findbyRecommentId(recommentDto.getId()).getLoginId().equals(user.getLoginId())) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN);
        }
        recommentService.deleteRecomment(recommentDto);

        return "redirect:" + redirect;
    }
}
