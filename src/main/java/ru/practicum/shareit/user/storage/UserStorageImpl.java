package ru.practicum.shareit.user.storage;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import ru.practicum.shareit.exception.DuplicatedDataException;
import ru.practicum.shareit.user.model.User;

import java.util.*;

@Repository
@Slf4j
public class UserStorageImpl implements UserStorage {
    private final Map<Long, User> users = new HashMap<>();
    private final Set<String> emails = new HashSet<>();
    private long id = 1;

    @Override
    public User create(User user) {
        if (!emails.add(user.getEmail())) {
            throw new DuplicatedDataException("Пользователь с таким email уже есть");
        }

        user.setId(id);
        users.put(id++, user);
        return user;
    }

    @Override
    public User update(User newUser) {
        User user = users.get(newUser.getId());
        if (newUser.getEmail() != null && !user.getEmail().equals(newUser.getEmail()) &&
                !emails.add(newUser.getEmail())) {
            throw new DuplicatedDataException("Пользователь с таким email уже есть");
        }

        if (newUser.getName() != null) {
            user.setName(newUser.getName());
        }

        if (newUser.getEmail() != null) {
            emails.remove(user.getEmail());
            user.setEmail(newUser.getEmail());
        }

        return user;
    }

    @Override
    public Optional<User> getUserById(long id) {
        return Optional.ofNullable(users.get(id));
    }

    @Override
    public List<User> getAllUsers() {
        return users.values().stream().toList();
    }

    @Override
    public void delete(long id) {
        emails.remove(getUserById(id).get().getEmail());
        users.remove(id);
    }
}