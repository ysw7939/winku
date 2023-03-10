package com.example.winku.repository.comment;

import com.example.winku.domain.comment.Comment;
import com.example.winku.domain.feed.Feed;
import com.example.winku.domain.user.User;
import com.example.winku.dto.comment.CreateCommentDto;
import com.example.winku.dto.comment.DeleteCommentDto;
import com.example.winku.dto.feed.ReadFeedDto;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Repository
public class JdbcTemplateCommentRepository implements CommentRepository{
    private final NamedParameterJdbcTemplate jdbcTemplate;
    private final SimpleJdbcInsert jdbcInsert;

    public JdbcTemplateCommentRepository(DataSource dataSource) {
        jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
        jdbcInsert = new SimpleJdbcInsert(dataSource);
    }

    @Override
    public Comment save(Comment comment) {
        String sql = "insert into comment (feed_id, login_id, user_name, content, profile) values (:feed_id, :login_id, :user_name, :content, :profile)";
        MapSqlParameterSource param = new MapSqlParameterSource()
                .addValue("feed_id", comment.getFeedId())
                .addValue("login_id", comment.getLoginId())
                .addValue("user_name", comment.getUserName())
                .addValue("content",comment.getContent())
                .addValue("profile", comment.getProfile());
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(sql, param, keyHolder);
        comment.setId(keyHolder.getKey().longValue());
        return comment;
    }

    @Override
    public List<Comment> findAll() {
        String sql = "select * from comment";
        return jdbcTemplate.query(sql, commentRowMapper());
    }

    private RowMapper<Comment> commentRowMapper() {
        return BeanPropertyRowMapper.newInstance(Comment.class);
    }

    @Override
    public List<Comment> findAllbyFeedId(long feedId) {
        String sql = "select * from comment";
        List<Comment> commentStoreList = jdbcTemplate.query(sql, commentRowMapper());
        List<Comment> commentList = new ArrayList<>();
        for (Comment comment : commentStoreList) {
            if (comment.getFeedId() == feedId) {
                commentList.add(comment);
            }
        }

        return commentList;
    }

    @Override
    public Comment createComment(CreateCommentDto commentDto) {
        Comment comment = new Comment(commentDto.getFeedId(),commentDto.getLoginId(), commentDto.getUserName(), commentDto.getContent(), commentDto.getProfile());
        String sql = "insert into comment (feed_id, login_id, user_name, content, profile) values (:feed_id, :login_id, :user_name, :content, :profile)";
        MapSqlParameterSource param = new MapSqlParameterSource()
                .addValue("feed_id", comment.getFeedId())
                .addValue("login_id", comment.getLoginId())
                .addValue("user_name", comment.getUserName())
                .addValue("content", comment.getContent())
                .addValue("profile", comment.getProfile());
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(sql, param, keyHolder);
        comment.setId(keyHolder.getKey().longValue());
        return comment;
    }

    @Override
    public void deleteComment(DeleteCommentDto commentDto) {
        String sql = "delete from comment where id=:id";
        MapSqlParameterSource param = new MapSqlParameterSource()
                .addValue("id", commentDto.getId());
        jdbcTemplate.update(sql, param);
    }

    @Override
    public Comment findCommentId(Long commentId) {
        String sql = "select * from comment where id = :id";
        MapSqlParameterSource param = new MapSqlParameterSource()
                .addValue("id", commentId);
        Comment comment = jdbcTemplate.queryForObject(sql, param, commentRowMapper());
        return comment;
    }
}
