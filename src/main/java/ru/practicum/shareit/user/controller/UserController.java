package ru.practicum.shareit.user.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.practicum.shareit.user.dto.UserDto;
import ru.practicum.shareit.user.service.UserService;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("users")
public class UserController {
    private final UserService userService;

    @GetMapping
    public List<UserDto> getAllUsers() {
        log.info("Получен HTTP-запрос по адресу /users (метод GET). Вызван метод getAllUsers()");
        return userService.getAllUsers();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserDto create(@Valid @RequestBody UserDto userDto) {
        log.info("Получен HTTP-запрос по адресу /users (метод POST). "
                + "Вызван метод create(@Valid @RequestBody UserDto userDto)");
        return userService.create(userDto);
    }

    @PatchMapping("/{userId}")
    public UserDto update(@RequestBody UserDto newUserDto, @PathVariable long userId) {
        log.info("Получен HTTP-запрос по адресу /users/{userId} (метод PATCH). "
                + "Вызван метод update(@Valid @RequestBody UserDto newUserDto)");
        return userService.update(newUserDto, userId);
    }

    @DeleteMapping("/{userId}")
    public void delete(@PathVariable long userId) {
        log.info("Получен HTTP-запрос по адресу /users/{userId} (метод DELETE). " +
                " Вызван метод delete(@PathVariable int userId)");
        userService.delete(userId);
    }

    @GetMapping("/{userId}")
    public UserDto getUserById(@PathVariable long userId) {
        log.info("Получен HTTP-запрос по адресу /users/{userId} (метод GET). " +
                " Вызван метод getUser(@PathVariable int userId)");
        return userService.getUserById(userId);
    }
}