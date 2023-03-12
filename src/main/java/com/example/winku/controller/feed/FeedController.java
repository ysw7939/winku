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
import com.example.winku.dto.feed.ReadFeedDto;
import com.example.winku.dto.recomment.CreateRecommentDto;
import com.example.winku.dto.recomment.DeleteRecommentDto;
import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpRequest;
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
    public String feedWrite(@Validated @ModelAttribute CreateFeedDto feedDto,@RequestParam String redirect, BindingResult bindingResult, Model model, HttpServletRequest request, HttpServletResponse response) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("feeds", feedService.combineReadFeedDto());
            return redirect;
        }

        feedService.createFeed(feedDto);
        return "redirect:" + redirect;
    }



    @PostMapping("/feed/deleteFeed")
    public String feedDelete(@ModelAttribute DeleteFeedDto feedDto, @SessionAttribute(name = "user") User user, @RequestParam String redirect) {
        if (!feedService.findFeedId(feedDto.getId()).getLoginId().equals(user.getLoginId())) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN);
        }
        feedService.deleteFeed(feedDto);
        return "redirect:" + redirect;
    }



    @GetMapping("/feed/{loginId}/mypage")
    public String Mylist(@PathVariable String loginId, @SessionAttribute(name = "user") User user, Model model) {
        List<ReadFeedDto> feeds = feedService.findAllbyLoginId(loginId);
        if (!loginId.equals(user.getLoginId())) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN);
        }
        model.addAttribute("myfeed", feeds);
        model.addAttribute("createFeedDto", new Feed());
        return "feed/time-line";


    }


}
