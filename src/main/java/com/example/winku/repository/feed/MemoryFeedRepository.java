package com.example.winku.repository.feed;

import com.example.winku.domain.comment.Comment;
import com.example.winku.domain.feed.Feed;
import com.example.winku.dto.feed.CreateFeedDto;
import com.example.winku.dto.feed.DeleteFeedDto;
import com.example.winku.dto.feed.ReadFeedDto;
import com.example.winku.repository.feed.FeedRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.*;

@Repository
public class MemoryFeedRepository implements FeedRepository {

    @Value("${file.dir}")
    private String fileDir;

    public static final Map<Long, Feed> store = new HashMap<>();
    private static long sequence = 0L;
    private Date date = new Date();

    private String storeFile(MultipartFile multipartFile) throws IOException {
        String fullPath = fileDir + multipartFile.getOriginalFilename();
        File file = new File(fullPath);
        multipartFile.transferTo(file);
        return multipartFile.getOriginalFilename();
    }


    @Override
    public Feed save(Feed feed) {
        feed.setId(++sequence);
        feed.setDate(date);
        store.put(feed.getId(),feed);
        return feed;
    }

    @Override
    public Feed createFeed(CreateFeedDto feedDto) {
        Feed saveFeed = new Feed();

        String imgPath = null;
        try {
            imgPath = storeFile(feedDto.getImgPath());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        saveFeed.setId(++sequence);
        saveFeed.setDate(date);
        saveFeed.setName(feedDto.getName());
        saveFeed.setContent(feedDto.getContent());
        saveFeed.setProfile(feedDto.getProfile());
        saveFeed.setImgPath(imgPath);
        return store.put(saveFeed.getId(), saveFeed);
    }

    @Override
    public List<Feed> findAll() {
        return new ArrayList<>(store.values());
    }

    @Override
    public List<ReadFeedDto> injectFeedIntoDto() {
        List<Feed> feedList = new ArrayList<>(store.values());
        List<ReadFeedDto> readFeedDtoList = new ArrayList<>();
        for (Feed feed : feedList) {
            ReadFeedDto readFeedDto = new ReadFeedDto(feed.getId(), feed.getDate(), feed.getName(), feed.getProfile(), feed.getImgPath(), feed.getContent(), feed.getViews(), feed.getLikes(), feed.getDislike(), feed.getComments());
            readFeedDtoList.add(readFeedDto);
        }

        return readFeedDtoList;
    }

    @Override
    public void deleteFeed(DeleteFeedDto feedDto) {
        store.remove(feedDto.getId());
    }

    @Override
    public void clearStore() {
        store.clear();
    }


}
