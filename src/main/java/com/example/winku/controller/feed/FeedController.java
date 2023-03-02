package com.example.winku.controller.feed;

import com.example.winku.Service.comment.CommentServiceImpl;
import com.example.winku.Service.feed.FeedServiceImpl;
import com.example.winku.Service.recomment.RecommentServiceImpl;
import com.example.winku.domain.comment.Comment;
import com.example.winku.domain.comment.Recomment;
import com.example.winku.domain.feed.Feed;
import com.example.winku.domain.user.User;
import com.example.winku.dto.comment.CreateCommentDto;
import com.example.winku.dto.comment.DeleteCommentDto;
import com.example.winku.dto.feed.CreateFeedDto;
import com.example.winku.dto.feed.DeleteFeedDto;
import com.example.winku.dto.recomment.CreateRecommentDto;
import com.example.winku.dto.recomment.DeleteRecommentDto;
import jakarta.annotation.PostConstruct;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@Controller
public class FeedController {
    final FeedServiceImpl feedService;
    final CommentServiceImpl commentService;
    final RecommentServiceImpl recommentService;

    public FeedController(FeedServiceImpl feedService, CommentServiceImpl commentService, RecommentServiceImpl recommentService) {
        this.feedService = feedService;
        this.commentService = commentService;
        this.recommentService = recommentService;
    }

    @GetMapping("/feed/index")
    public String list(Model model){
        model.addAttribute("feeds", feedService.combineReadFeedDto());
        model.addAttribute("createFeedDto", new Feed());
        return "feed/index";
    }

    @PostMapping("/feed/writeFeed")
    public String feedWrite(@Validated @ModelAttribute CreateFeedDto feedDto, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("feeds", feedService.combineReadFeedDto());
            return "feed/index";
        }
        feedService.createFeed(feedDto);
        return "redirect:/feed/index";
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

    @PostMapping("/feed/deleteFeed")
    public String feedDelete(@ModelAttribute DeleteFeedDto feedDto) {
        feedService.deleteFeed(feedDto);
        return "redirect:/feed/index";
    }

    @PostMapping("/feed/deleteComment")
    public String CommentDelete(@ModelAttribute DeleteCommentDto commentDto) {
        commentService.deleteComment(commentDto);
        return "redirect:/feed/index";
    }
    @PostMapping("/feed/deleteRecomment")
    public String RecommentDelete(@ModelAttribute DeleteRecommentDto recommentDto) {
        recommentService.deleteRecomment(recommentDto);
        return "redirect:/feed/index";
    }

    @GetMapping("/feed/{loginId}/mypage/")
    public String Mylist(@PathVariable String loginId, @SessionAttribute(name = "user") User user, Model model) {
        List<Feed> feeds = feedService.findAllbyLoginId(loginId);
        if (!loginId.equals(user.getLoginId())) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN);
        }
        model.addAttribute("myfeed", feeds);
        return "feed/time-line";


    }

    @PostConstruct
    public void init() {
        feedService.saveFeed(new Feed("test","양수원", "/images/resources/friend-avatar10.jpg","resources/user-post.jpg","내용들 입니다."));
        feedService.saveFeed(new Feed("test","양수원", "/images/resources/friend-avatar10.jpg","resources/user-post.jpg","내용들 입니다."));
        commentService.saveComment(new Comment(1L, "Su Won", "내용", "/images/resources/friend-avatar10.jpg"));
        commentService.saveComment(new Comment(1L, "Su Wan", "내용", "/images/resources/friend-avatar10.jpg"));
        commentService.saveComment(new Comment(1L, "Su Wan", "내용", "/images/resources/friend-avatar10.jpg"));
        commentService.saveComment(new Comment(2L, "Su Wbn", "내용", "/images/resources/friend-avatar10.jpg"));
        commentService.saveComment(new Comment(2L, "Su Wdn", "내용", "/images/resources/friend-avatar10.jpg"));
        recommentService.saveRecomment(new Recomment(1L, "대댓글 내용입니다!!!!!!!!!", "양수원", "/images/resources/friend-avatar10.jpg"));
        recommentService.saveRecomment(new Recomment(1L, "대댓글 내용입니다!!!!!!!!!", "양수원", "/images/resources/friend-avatar10.jpg"));
        recommentService.saveRecomment(new Recomment(2L, "대댓글 내용입니다!!!!!!!!!", "양수원", "/images/resources/friend-avatar10.jpg"));
        recommentService.saveRecomment(new Recomment(3L, "대댓글 내용입니다!!!!!!!!!", "양수원", "/images/resources/friend-avatar10.jpg"));
    }


}
