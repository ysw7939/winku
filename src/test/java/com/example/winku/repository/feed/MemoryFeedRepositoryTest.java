package com.example.winku.repository.feed;

import com.example.winku.domain.feed.Feed;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.*;

public class MemoryFeedRepositoryTest {
    MemoryFeedRepository memoryFeedRepository = new MemoryFeedRepository();

    @AfterEach
    void afterEach() {
        memoryFeedRepository.clearStore();
    }
    @Test
    void save() {
        //given
        Feed feed1 = new Feed("양수원", "/images/resources/friend-avatar10.jpg","/images/resources/user-post.jpg","내용들 입니다.");
        Feed feed2 = new Feed("양수원", "/images/resources/friend-avatar10.jpg", "/images/resources/user-post.jpg", "내용들 입니다.");

        //situation
        memoryFeedRepository.save(feed1);
        memoryFeedRepository.save(feed2);

        //then
        List<Feed> feeds = memoryFeedRepository.findAll();
        Assertions.assertThat(feeds.size()).isEqualTo(2);
    }



    @Test
    void findAll() {
    }

    @Test
    void saveFeed() {
    }
}
