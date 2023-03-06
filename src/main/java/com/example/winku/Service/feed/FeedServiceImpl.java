package com.example.winku.Service.feed;

import com.example.winku.Service.comment.CommentService;
import com.example.winku.Service.comment.CommentServiceImpl;
import com.example.winku.domain.comment.Comment;
import com.example.winku.domain.feed.Feed;
import com.example.winku.dto.comment.DeleteCommentDto;
import com.example.winku.dto.feed.CreateFeedDto;
import com.example.winku.dto.feed.DeleteFeedDto;
import com.example.winku.dto.feed.ReadFeedDto;
import com.example.winku.repository.feed.FeedRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class FeedServiceImpl implements FeedService {
    private final FeedRepository feedRepository;
    private final CommentService commentService;
    @Autowired
    public FeedServiceImpl(FeedRepository feedRepository, CommentService commentService) {
        this.feedRepository = feedRepository;
        this.commentService = commentService;
    }

    @Override
    public List<Feed> findFeeds() {

        return feedRepository.findAll();
    }

    @Override
    public Feed saveFeed(Feed feed) {
        return feedRepository.save(feed);
    }

    @Override
    public Feed createFeed(CreateFeedDto feedDto) {
        return feedRepository.createFeed(feedDto);
    }

    @Override
    public List<ReadFeedDto> combineReadFeedDto() {
        List<ReadFeedDto> dtoList =  feedRepository.injectFeedIntoDto();


        for (ReadFeedDto readFeedDto : dtoList) {
            readFeedDto.setCommentList(commentService.findAllbyFeedId(readFeedDto.getId()));
        }
        return dtoList;
    }

    @Override
    public void deleteFeed(DeleteFeedDto feedDto) {
        for (Comment comment :  commentService.findAllbyFeedId(feedDto.getId())) {
            DeleteCommentDto commentDto = new DeleteCommentDto(comment.getId());
            commentService.deleteComment(commentDto);
        }
        feedRepository.deleteFeed(feedDto);
    }

    @Override
    public List<Feed> findAllbyLoginId(String loginId) {
        return feedRepository.findAllbyLoginId(loginId);
    }

    @Override
    public Feed findFeedId(Long feedId) {
        return feedRepository.findFeedId(feedId);
    }
}
