package com.example.winku.repository.user;

import com.example.winku.domain.user.User;
import com.example.winku.dto.user.ProfileDto;

import java.util.Optional;

public interface UserRepository {
    User save(User user);

    Optional<User> findById(String id);

    Optional<User> findByLoginId(String loginId);

    Optional<User> findByName(String name);

    void profileUpdate(ProfileDto profileDto);

    void delete(String id);

    void clearStore();

}
