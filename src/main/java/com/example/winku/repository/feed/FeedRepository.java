package com.example.winku.repository.feed;

import com.example.winku.domain.comment.Comment;
import com.example.winku.domain.feed.Feed;
import com.example.winku.dto.feed.CreateFeedDto;
import com.example.winku.dto.feed.DeleteFeedDto;
import com.example.winku.dto.feed.ReadFeedDto;

import java.util.List;

public interface FeedRepository {
    Feed save(Feed feed);

    Feed createFeed(CreateFeedDto feedDto);
    List<Feed> findAll();

    void clearStore();

    List<ReadFeedDto> injectFeedIntoDto();

    void deleteFeed(DeleteFeedDto feedDto);
}
