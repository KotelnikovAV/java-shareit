package ru.practicum.shareit.user.storage;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import ru.practicum.shareit.user.model.User;

import java.util.*;

@Repository
@Slf4j
public class UserStorageImpl implements UserStorage {
    private final Map<Long, User> users = new HashMap<>();
    private long id = 1;

    @Override
    public User create(User user) {
        user.setId(id);
        users.put(id++, user);
        return user;
    }

    @Override
    public User update(User newUser) {
        User user = users.get(newUser.getId());

        if (newUser.getName() != null) {
            user.setName(newUser.getName());
        }

        if (newUser.getEmail() != null) {
            user.setEmail(newUser.getEmail());
        }

        return user;
    }

    @Override
    public User getUserById(long id) {
        return users.get(id);
    }

    @Override
    public List<User> getAllUsers() {
        return users.values().stream().toList();
    }

    @Override
    public void delete(long id) {
        users.remove(id);
    }

    @Override
    public boolean checkUser(long id) {
        return users.containsKey(id);
    }

    @Override
    public boolean checkEmail(String email, long userId) {
        return users.values().stream()
                .filter(user -> user.getId() != userId)
                .map(User::getEmail)
                .anyMatch(emailUser -> emailUser.equals(email));
    }
}