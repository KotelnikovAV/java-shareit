package ru.practicum.shareit.item.storage;

import ru.practicum.shareit.item.model.Item;

import java.util.List;

public interface ItemStorage {
    Item create(Item item);

    Item update(Item item);

    Item getItemById(long id);

    List<Item> getAllItemsByOwner(long ownerId);

    List<Item> searchByText(String text);

    boolean accessVerification(long id, long ownerId);

    boolean checkItem(long itemId);
}
