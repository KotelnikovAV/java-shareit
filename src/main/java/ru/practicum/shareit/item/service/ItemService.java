package ru.practicum.shareit.item.service;

import ru.practicum.shareit.item.dto.ItemDto;

import java.util.List;

public interface ItemService {
    ItemDto create(ItemDto item, long id);
    ItemDto update(ItemDto itemDto, long id, long ownerId);
    ItemDto getItemById(long id);
    List<ItemDto> getAllItemsByOwner(long ownerId);
    List<ItemDto> searchByText(String text);
}
