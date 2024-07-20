package ru.practicum.shareit.user.storage;

import ru.practicum.shareit.user.model.User;

import java.util.List;

public interface UserStorage {
    User create(User user);

    User update(User user);

    User getUserById(long id);

    List<User> getAllUsers();

    void delete(long id);

    boolean checkUser(long id);

    boolean checkEmail(String email, long userId);
}
