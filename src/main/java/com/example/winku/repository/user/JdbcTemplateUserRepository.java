package com.example.winku.repository.user;

import com.example.winku.domain.user.User;
import com.example.winku.dto.user.ProfileDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.web.multipart.MultipartFile;

import javax.sql.DataSource;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class JdbcTemplateUserRepository implements UserRepository {
    @Value("${file.repository}")
    private String fileDir;
    private final NamedParameterJdbcTemplate jdbcTemplate;
    private final SimpleJdbcInsert jdbcInsert;

    private String storeFile(MultipartFile multipartFile) throws IOException {
        String fullPath = fileDir + multipartFile.getOriginalFilename();
        File file = new File(fullPath);
        multipartFile.transferTo(file);
        return multipartFile.getOriginalFilename();
    }

    public JdbcTemplateUserRepository(DataSource dataSource) {
        jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
        jdbcInsert = new SimpleJdbcInsert(dataSource)
                .withTableName("users")
                .usingGeneratedKeyColumns("id");
    }

    @Override
    public User save(User user) {
        String sql = "insert into users (name, password, login_id, email, gender) values (:name, :password, :login_id, email, gender)";
        MapSqlParameterSource param = new MapSqlParameterSource()
                .addValue("name", user.getName())
                .addValue("password", user.getPassword())
                .addValue("login_id", user.getLoginId())
                .addValue("email", user.getEmail())
                .addValue("gender", user.getGender());
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(sql, param, keyHolder);
        user.setId(keyHolder.getKey().toString());
        return user;
    }

    private RowMapper<User> userRowMapper() {
        return BeanPropertyRowMapper.newInstance(User.class);
    }

    @Override
    public Optional<User> findById(String id) {
        String sql = "select * from users where id = :id";
        MapSqlParameterSource param = new MapSqlParameterSource().addValue("id", id);
        User user = jdbcTemplate.queryForObject(sql, param, userRowMapper());
        return Optional.of(user);
    }

    @Override
    public Optional<User> findByLoginId(String loginId) {
        String sql = "select * from users where login_id = :login_id";
        MapSqlParameterSource param = new MapSqlParameterSource()
                .addValue("loginId", loginId);
        User user = jdbcTemplate.queryForObject(sql, param, userRowMapper());
        return Optional.of(user);
    }


    @Override
    public void profileUpdate(ProfileDto profileDto) {
        String imgPath = null;
        try {
            imgPath = storeFile(profileDto.getProfileImg());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        String sql = "update users set profile_img=:profile_img where id=:id";
        MapSqlParameterSource param = new MapSqlParameterSource()
                .addValue("profile_img", imgPath)
                .addValue("id" , profileDto.getUserId());
        jdbcTemplate.update(sql, param);
    }

    @Override
    public void delete(String id) {
        String sql = "delete from users where id=:id";
        MapSqlParameterSource param = new MapSqlParameterSource()
                .addValue("id", id);
        jdbcTemplate.update(sql, param);
    }


}
