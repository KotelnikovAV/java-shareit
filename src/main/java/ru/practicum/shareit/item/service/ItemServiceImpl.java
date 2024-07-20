package ru.practicum.shareit.item.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.practicum.shareit.exception.DataAccessException;
import ru.practicum.shareit.exception.NotFoundException;
import ru.practicum.shareit.item.dto.ItemDto;
import ru.practicum.shareit.item.mapper.ItemMapper;
import ru.practicum.shareit.item.model.Item;
import ru.practicum.shareit.item.storage.ItemStorage;
import ru.practicum.shareit.user.storage.UserStorage;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class ItemServiceImpl implements ItemService {
    private final UserStorage userStorage;
    private final ItemStorage itemStorage;
    private final ItemMapper itemMapper;

    @Override
    public ItemDto create(ItemDto itemDto, long ownerId) {
        log.info("Начало процесса создания предмета с ownerId = {}", ownerId);

        if (!userStorage.checkUser(ownerId)) {
            log.info("Начало проверки наличия владельца при создании");
            throw new NotFoundException("Такого владельца не существует");
        }

        itemDto.setOwnerId(ownerId);
        Item item = itemStorage.create(itemMapper.itemDtoToItem(itemDto));
        log.info("Предмет создан");
        return itemMapper.itemToItemDto(item);
    }

    @Override
    public ItemDto update(ItemDto itemDto, long id, long ownerId) {
        log.info("Начало процесса обновления предмета с id = {} и ownerId = {}", id, ownerId);

        if (!itemStorage.checkItem(id)) {
            log.info("Начало проверки наличия предмета при обновлении");
            throw new NotFoundException("Такого предмета не существует");
        }

        if (!userStorage.checkUser(ownerId)) {
            log.info("Начало проверки наличия владельца при обновлении");
            throw new NotFoundException("Такого владельца не существует");
        }

        if (!itemStorage.accessVerification(id, ownerId)) {
            log.info("Начало проверки наличия доступа к обновлению");
            throw new DataAccessException("У вас нет доступа к этой информации");
        }

        itemDto.setId(id);
        Item item = itemStorage.update(itemMapper.itemDtoToItem(itemDto));
        log.info("Предмет обновлен");
        return itemMapper.itemToItemDto(item);
    }

    @Override
    public ItemDto getItemById(long id) {
        log.info("Начало процесса получения предмета по id = {}", id);

        if (!itemStorage.checkItem(id)) {
            log.info("Начало проверки наличия предмета при получении");
            throw new NotFoundException("Такого предмета не существует");
        }

        Item item = itemStorage.getItemById(id);
        return itemMapper.itemToItemDto(item);
    }

    @Override
    public List<ItemDto> getAllItemsByOwner(long ownerId) {
        log.info("Начало процесса получения всех предметов пользователя с ownerId = {}", ownerId);

        if (!userStorage.checkUser(ownerId)) {
            log.info("Начало проверки наличия владельца при получении списка вещей");
            throw new NotFoundException("Такого владельца не существует");
        }

        List<Item> items = itemStorage.getAllItemsByOwner(ownerId);
        log.info("Список всех предметов получен");
        return items.stream()
                .map(itemMapper::itemToItemDto)
                .toList();
    }

    @Override
    public List<ItemDto> searchByText(String text) {
        log.info("Начало процесса получения предметов по поиску с text = {}", text);

        if (text.isBlank()) {
            log.info("Текст поиска пустой - список предметов пустой");
            return new ArrayList<>();
        }

        List<Item> items = itemStorage.searchByText(text.toLowerCase());
        log.info("Список предметов по поиску получен");
        return items.stream()
                .map(itemMapper::itemToItemDto)
                .toList();
    }
}