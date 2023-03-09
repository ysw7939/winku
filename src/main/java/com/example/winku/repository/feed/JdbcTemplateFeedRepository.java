package com.example.winku.repository.feed;

import com.example.winku.domain.feed.Feed;
import com.example.winku.dto.feed.CreateFeedDto;
import com.example.winku.dto.feed.DeleteFeedDto;
import com.example.winku.dto.feed.ReadFeedDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import javax.sql.DataSource;
import java.io.File;
import java.io.IOException;
import java.util.*;
@Repository
public class JdbcTemplateFeedRepository implements FeedRepository{
    @Value("${file.repository}")
    private String fileDir;
    private final NamedParameterJdbcTemplate jdbcTemplate;
    private final SimpleJdbcInsert jdbcInsert;
    private Date date = new Date();

    private String storeFile(MultipartFile multipartFile) throws IOException {
        String fullPath = fileDir + multipartFile.getOriginalFilename();
        File file = new File(fullPath);
        multipartFile.transferTo(file);
        return multipartFile.getOriginalFilename();
    }

    public JdbcTemplateFeedRepository(DataSource dataSource) {
        jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
        jdbcInsert = new SimpleJdbcInsert(dataSource)
                .withTableName("feed")
                .usingGeneratedKeyColumns("id");
    }

    @Override
    public Feed save(Feed feed) {
        SqlParameterSource param = new BeanPropertySqlParameterSource(feed);
        Number key = jdbcInsert.executeAndReturnKey(param);
        feed.setId(key.longValue());

        return feed;
    }

    @Override
    public Feed createFeed(CreateFeedDto feedDto) {
        Feed saveFeed = new Feed();

        String imgPath = null;
        if ( !feedDto.getImgPath().isEmpty()) {
            try {
                imgPath = storeFile(feedDto.getImgPath());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }


        saveFeed.setDate(date);
        saveFeed.setName(feedDto.getName());
        saveFeed.setLoginId(feedDto.getLoginId());
        saveFeed.setContent(feedDto.getContent());
        saveFeed.setProfile(feedDto.getProfile());
        saveFeed.setImgPath(imgPath);
        SqlParameterSource param = new BeanPropertySqlParameterSource(saveFeed);
        Number key = jdbcInsert.executeAndReturnKey(param);
        saveFeed.setId(key.longValue());
        return saveFeed;
    }

    @Override
    public List<Feed> findAll() {
        String sql = "select * from feed";
        return jdbcTemplate.query(sql, feedRowMapper());
    }

    private RowMapper<Feed> feedRowMapper() {
        return BeanPropertyRowMapper.newInstance(Feed.class);
    }


    @Override
    public List<ReadFeedDto> injectFeedIntoDto() {
        return null;
    }

    @Override
    public void deleteFeed(DeleteFeedDto feedDto) {
        String sql = "delete form feed where id=:id";
        MapSqlParameterSource param = new MapSqlParameterSource()
                .addValue("id", feedDto.getId());
        jdbcTemplate.update(sql, param);

    }

    @Override
    public List<ReadFeedDto> findAllbyLoginId(String loginId) {
        String sql = "select * from feed where loginId = :loginId";
        MapSqlParameterSource param = new MapSqlParameterSource()
                .addValue("loginId", loginId);
        List<Feed> FeedList = jdbcTemplate.query(sql, param,feedRowMapper());
        List<ReadFeedDto> readFeedDtoList = new ArrayList<>();
        for (Feed feed : FeedList) {
            ReadFeedDto readFeedDto = new ReadFeedDto(feed.getId(),feed.getLoginId(), feed.getDate(), feed.getName(), feed.getProfile(), feed.getImgPath(), feed.getContent(), feed.getViews(), feed.getLikes(), feed.getDislike(), feed.getComments());
            readFeedDtoList.add(readFeedDto);
        }
        return readFeedDtoList;

    }

    @Override
    public Feed findFeedId(Long feedId) {
        String sql = "select * from feed where id = :id";
        MapSqlParameterSource param = new MapSqlParameterSource()
                .addValue("id", feedId);

        Feed feed = jdbcTemplate.queryForObject(sql, param, feedRowMapper());
        return feed;

    }
}
