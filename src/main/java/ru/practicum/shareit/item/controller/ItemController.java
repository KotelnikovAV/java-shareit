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
        log.info("Получен HTTP-запрос по адресу /items (метод POST). Вызван метод " +
                "create(@Valid @RequestBody ItemDto item, @RequestHeader(\"X-Sharer-User-Id\") long ownerId)");
        return itemService.create(item, ownerId);
    }

    @PatchMapping("/{itemId}")
    public ItemDto update(@RequestBody ItemDto newItem,
                          @PathVariable long itemId,
                          @RequestHeader("X-Sharer-User-Id") long ownerId) {
        log.info("Получен HTTP-запрос по адресу /items/{itemId} (метод PATCH). "
                + "Вызван метод update(@Valid @RequestBody ItemDto newItem, @PathVariable long itemId, " +
                "@RequestHeader(\"X-Sharer-User-Id\") long ownerId)");
        return itemService.update(newItem, itemId, ownerId);
    }

    @GetMapping("/{itemId}")
    public ItemDto getItemById(@PathVariable long itemId) {
        log.info("Получен HTTP-запрос по адресу /items/{itemId} (метод GET). "
                + "Вызван метод getItemById(@PathVariable long itemId)");
        return itemService.getItemById(itemId);
    }

    @GetMapping
    public List<ItemDto> getAllItemsByOwner(@RequestHeader("X-Sharer-User-Id") long ownerId) {
        log.info("Получен HTTP-запрос по адресу /items (метод GET). "
                + "Вызван метод getAllItemsByOwner(@RequestHeader(\"X-Sharer-User-Id\"");
        return itemService.getAllItemsByOwner(ownerId);
    }

    @GetMapping("/search")
    public List<ItemDto> searchByText(@RequestParam(defaultValue = "") String text) {
        log.info("Получен HTTP-запрос по адресу /items/search (метод GET). "
                + "Вызван метод searchByText(@RequestParam(defaultValue = \"\") String text");
        return itemService.searchByText(text);
    }
}