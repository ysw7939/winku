package com.example.winku.Service.feed;

import com.example.winku.domain.comment.Comment;
import com.example.winku.domain.feed.Feed;
import com.example.winku.dto.feed.CreateFeedDto;
import com.example.winku.dto.feed.DeleteFeedDto;
import com.example.winku.dto.feed.ReadFeedDto;

import java.util.List;

public interface FeedService {
    List<Feed> findFeeds();

    Feed saveFeed(Feed feed);
    Feed createFeed(CreateFeedDto feedDto);

    List<ReadFeedDto> combineReadFeedDto();

    void deleteFeed(DeleteFeedDto feedDto);

    List<Feed> findAllbyLoginId(String loginId);

    Feed findFeedId(Long feedId);
}
