package ru.practicum.shareit.user.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.practicum.shareit.exception.DuplicatedDataException;
import ru.practicum.shareit.exception.NotFoundException;
import ru.practicum.shareit.user.dto.UserDto;
import ru.practicum.shareit.user.mapper.UserMapper;
import ru.practicum.shareit.user.model.User;
import ru.practicum.shareit.user.storage.UserStorage;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserStorage userStorage;
    private final UserMapper userMapper;

    @Override
    public UserDto create(UserDto user) {
        log.info("Начало процесса создания пользователя");
        User createdUser = userStorage.create(userMapper.userDtoToUser(user));
        log.info("Пользователь создан");
        return userMapper.userToUserDto(createdUser);
    }

    @Override
    public UserDto update(UserDto user, long userId) {
        log.info("Начало процесса обновления пользователя с userId = {}", userId);

        if (userStorage.getUserById(userId).isEmpty()) {
            throw new NotFoundException("Такого пользователя нет");
        }

        user.setId(userId);
        User updatedUser = userStorage.update(userMapper.userDtoToUser(user));
        log.info("Пользователь обновлен");
        return userMapper.userToUserDto(updatedUser);
    }

    @Override
    public UserDto getUserById(long id) {
        log.info("Начало процесса получения пользователя по id = {}", id);
        Optional<User> user = userStorage.getUserById(id);

        if (user.isEmpty()) {
            throw new NotFoundException("Такого пользователя нет");
        }

        log.info("Пользователь получен");
        return userMapper.userToUserDto(user.get());
    }

    @Override
    public List<UserDto> getAllUsers() {
        log.info("Начало процесса получения всех пользователей");

        List<User> users = userStorage.getAllUsers();
        log.info("Список пользователей получен");
        return users.stream()
                .map(userMapper::userToUserDto)
                .toList();
    }

    @Override
    public void delete(long id) {
        log.info("Начало процесса удаления пользователя по id = {}", id);

        if (userStorage.getUserById(id).isEmpty()) {
            throw new NotFoundException("Такого пользователя нет");
        }

        userStorage.delete(id);
        log.info("Пользователь удален");
    }
}
