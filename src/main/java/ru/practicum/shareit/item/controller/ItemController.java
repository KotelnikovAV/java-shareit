package ru.practicum.shareit.item.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.practicum.shareit.item.dto.ItemDto;
import ru.practicum.shareit.item.service.ItemService;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("items")
public class ItemController {
    private final ItemService itemService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ItemDto create(@Valid @RequestBody ItemDto item, @RequestHeader("X-Sharer-User-Id") long ownerId) {
        log.info("Получен POST запрос на создание предмета {} пользователем с id = {}", item, ownerId);
        return itemService.create(item, ownerId);
    }

    @PatchMapping("/{itemId}")
    public ItemDto update(@RequestBody ItemDto newItem,
                          @PathVariable long itemId,
                          @RequestHeader("X-Sharer-User-Id") long ownerId) {
        log.info("Получен PATCH запрос на обновление предмета с itemId = {} от пользователя с ownerId = {}, " +
                "поля, которые нужно обновить: {}", itemId, ownerId, newItem);
        return itemService.update(newItem, itemId, ownerId);
    }

    @GetMapping("/{itemId}")
    public ItemDto getItemById(@PathVariable long itemId) {
        log.info("Получен GET запрос на получение предмета с itemId = {}", itemId);
        return itemService.getItemById(itemId);
    }

    @GetMapping
    public List<ItemDto> getAllItemsByOwner(@RequestHeader("X-Sharer-User-Id") long ownerId) {
        log.info("Получен GET запрос на получение всех предметов пользователя с ownerId = {}", ownerId);
        return itemService.getAllItemsByOwner(ownerId);
    }

    @GetMapping("/search")
    public List<ItemDto> searchByText(@RequestParam(defaultValue = "") String text) {
        log.info("Получен GET запрос на получение предмета по поиску text = {}", text);
        return itemService.searchByText(text);
    }
}