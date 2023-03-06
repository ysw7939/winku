package com.example.winku.controller.comment;

import com.example.winku.Service.comment.CommentServiceImpl;
import com.example.winku.Service.recomment.RecommentServiceImpl;
import com.example.winku.dto.comment.CreateCommentDto;
import com.example.winku.dto.comment.DeleteCommentDto;
import com.example.winku.dto.recomment.CreateRecommentDto;
import com.example.winku.dto.recomment.DeleteRecommentDto;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
@Controller
public class CommentController {
    final CommentServiceImpl commentService;
    final RecommentServiceImpl recommentService;

    public CommentController(CommentServiceImpl commentService, RecommentServiceImpl recommentService) {
        this.commentService = commentService;
        this.recommentService = recommentService;
    }
    @PostMapping("/feed/writeComment")
    public String commentWrite(@ModelAttribute CreateCommentDto commentDto) {
        commentService.createComment(commentDto);
        return "redirect:/feed/index";
    }

    @PostMapping("/feed/writeRecomment")
    public String RecommentWrite(@ModelAttribute CreateRecommentDto recommentDto) {
        recommentService.createRecomment(recommentDto);
        return "redirect:/feed/index";
    }

    @PostMapping("/feed/deleteComment")
    public String CommentDelete(@ModelAttribute DeleteCommentDto commentDto) {
        if(!commentService.findAllbyFeedId())
        commentService.deleteComment(commentDto);
        return "redirect:/feed/index";
    }
    @PostMapping("/feed/deleteRecomment")
    public String RecommentDelete(@ModelAttribute DeleteRecommentDto recommentDto) {
        recommentService.deleteRecomment(recommentDto);

        return "redirect:/feed/index";
    }
}
