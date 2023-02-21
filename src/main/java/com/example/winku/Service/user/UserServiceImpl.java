package com.example.winku.Service.user;

import com.example.winku.domain.user.User;
import com.example.winku.dto.user.SignInDto;
import com.example.winku.repository.user.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public Optional<User> signIn(SignInDto signInDto) {
        Optional<User> userOptional = userRepository.findByLoginId(signInDto.getLoginId());

        if (userOptional.isPresent()) {
            User user = userOptional.get();
            if (user.getPassword().equals(signInDto.getPassword())) {
                return userOptional;
            }
        }
        return Optional.empty();
    }

    @Override
    public User signUp(User user) {
        return userRepository.save(user);
    }

    @Override
    public Optional<User> findUserById(String id) {
        return userRepository.findById(id);
    }

    @Override
    public Optional<User> findUserByLoginId(String loginId) {
        return userRepository.findByLoginId(loginId);
    }

    @Override
    public Optional<User> findUserByName(String name) {
        return userRepository.findByName(name);
    }
}
