package com.example.winku.repository.user;

import com.example.winku.domain.user.User;

import java.util.Optional;

public interface UserRepository {
    User save(User user);

    Optional<User> findById(String id);

    Optional<User> findByLoginId(String loginId);

    Optional<User> findByName(String name);

    void update(String id, User user);

    void delete(String id);

    void clearStore();
}
