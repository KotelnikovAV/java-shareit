package ru.practicum.shareit.user.service;

import ru.practicum.shareit.user.dto.UserDto;

import java.util.List;

public interface UserService {
    UserDto create(UserDto user);

    UserDto update(UserDto user, long userId);

    UserDto getUserById(long id);

    List<UserDto> getAllUsers();

    void delete(long id);
}
