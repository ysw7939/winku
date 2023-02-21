package com.example.winku.repository.user;

import com.example.winku.domain.user.User;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Repository
public class MemoryUserRepository implements UserRepository {
    private static final Map<String, User> store = new HashMap<>();
    @Override
    public User save(User user) {
        UUID uuid = UUID.randomUUID();
        user.setId(uuid.toString());
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
