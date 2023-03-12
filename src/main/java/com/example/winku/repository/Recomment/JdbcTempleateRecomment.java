package com.example.winku.repository.Recomment;

import com.example.winku.domain.comment.Comment;
import com.example.winku.domain.comment.Recomment;
import com.example.winku.dto.recomment.CreateRecommentDto;
import com.example.winku.dto.recomment.DeleteRecommentDto;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
@Repository
public class JdbcTempleateRecomment implements RecommentRepository{
    private final NamedParameterJdbcTemplate jdbcTemplate;
    private final SimpleJdbcInsert jdbcInsert;

    private Date date = new Date();

    public JdbcTempleateRecomment(DataSource dataSource) {
        jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
        jdbcInsert = new SimpleJdbcInsert(dataSource)
                .withTableName("recomment")
                .usingGeneratedKeyColumns("id");
    }

    @Override
    public Recomment save(Recomment recomment) {
        recomment.setDate(date);
        String sql = "insert into recomment(login_id, comment_id, date, content, user_name, profile) values( :login_id, :comment_id, :date, :content, :user_name, :profile)";
        MapSqlParameterSource param = new MapSqlParameterSource()
                .addValue("login_id", recomment.getLoginId())
                .addValue("comment_id", recomment.getCommentId())
                .addValue("date", recomment.getDate())
                .addValue("content", recomment.getContent())
                .addValue("user_name", recomment.getUserName())
                .addValue("profile", recomment.getProfile());
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(sql, param, keyHolder);
        return recomment;
    }

    private RowMapper<Recomment> recommentRowMapper() {
        return BeanPropertyRowMapper.newInstance(Recomment.class);
    }

    @Override
    public List<Recomment> findAllbyCommentId(long commentId) {
        String sql = "select * from recomment";
        List<Recomment> recommentStoreList = jdbcTemplate.query(sql, recommentRowMapper());
        List<Recomment> recommentList = new ArrayList<>();
        for (Recomment recomment : recommentStoreList) {
            if (recomment.getCommentId() == commentId) {
                recommentList.add(recomment);
            }
        }
        return recommentList;
    }

    @Override
    public Recomment createRecomment(CreateRecommentDto recommentDto) {
        Recomment recomment = new Recomment(recommentDto.getCommentId(), recommentDto.getLoginId(), recommentDto.getContent(), recommentDto.getUserName(), recommentDto.getProfile());
        recomment.setDate(date);
        String sql = "insert into recomment(login_id, comment_id, date, content, user_name, profile) values( :login_id, :comment_id, :date, :content, :user_name, :profile)";
        MapSqlParameterSource param = new MapSqlParameterSource()
                .addValue("login_id", recomment.getLoginId())
                .addValue("comment_id", recomment.getCommentId())
                .addValue("date", recomment.getDate())
                .addValue("content", recomment.getContent())
                .addValue("user_name", recomment.getUserName())
                .addValue("profile", recomment.getProfile());
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(sql, param, keyHolder);
        return recomment;
    }

    @Override
    public void deleteRecomment(DeleteRecommentDto recommentDto) {
        String sql = "delete from recomment where id=:id";
        MapSqlParameterSource param = new MapSqlParameterSource()
                .addValue("id", recommentDto.getId());
        jdbcTemplate.update(sql, param);
    }

    @Override
    public Recomment recommentId(long recommentId) {
        String sql = "select * from recomment where id = :id";
        MapSqlParameterSource param = new MapSqlParameterSource()
                .addValue("id", recommentId);
        Recomment recomment = jdbcTemplate.queryForObject(sql, param, recommentRowMapper());
        return recomment;
    }
}
