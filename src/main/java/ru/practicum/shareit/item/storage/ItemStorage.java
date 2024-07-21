package ru.practicum.shareit.item.storage;

import ru.practicum.shareit.item.model.Item;

import java.util.List;
import java.util.Optional;

public interface ItemStorage {
    Item create(Item item);

    Item update(Item item);

    Optional<Item> getItemById(long id);

    List<Item> getAllItemsByOwner(long ownerId);

    List<Item> searchByText(String text);

    boolean accessVerification(long id, long ownerId);
}
