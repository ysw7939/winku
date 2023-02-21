package com.example.winku.Service.user;

import com.example.winku.domain.user.User;
import com.example.winku.dto.user.SignInDto;

import java.util.Optional;

public interface UserService {
    Optional<User> signIn(SignInDto signInDto);

    User signUp(User user);
    Optional<User> findUserById(String id);

    Optional<User> findUserByLoginId(String loginId);

    Optional<User> findUserByName(String name);
}
