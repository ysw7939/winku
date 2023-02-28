package com.example.winku.repository.user;

import com.example.winku.domain.user.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Repository
public class MemoryUserRepository implements UserRepository {
    @Value("${file.dir}")
    private String fileDir;

    private static final Map<String, User> store = new HashMap<>();

    private String storeFile(MultipartFile multipartFile) throws IOException {
        String fullPath = fileDir + multipartFile.getOriginalFilename();
        File file = new File(fullPath);
        multipartFile.transferTo(file);
        return multipartFile.getOriginalFilename();
    }
    @Override
    public User save(User user) {
        UUID uuid = UUID.randomUUID();
        user.setId(uuid.toString());
        user.setProfileImg("/images/resources/user.png");
        return store.put(user.getId(), user);
    }

    @Override
    public Optional<User> findById(String id) {
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public Optional<User> findByLoginId(String loginId) {
        return store.values().stream()
                .filter(user -> user.getLoginId().equals(loginId))
                .findAny();
    }

    @Override
    public Optional<User> findByName(String name) {
        return store.values().stream()
                .filter(user -> user.getName().equals(name))
                .findAny();
    }

    @Override
    public void update(String id, User user) {
        store.put(id, user);
    }

    @Override
    public void delete(String id) {
        store.remove(id);
    }

    @Override
    public void clearStore() {
        store.clear();
    }
}
