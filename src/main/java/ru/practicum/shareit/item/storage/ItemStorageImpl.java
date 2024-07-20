package ru.practicum.shareit.item.storage;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import ru.practicum.shareit.item.model.Item;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
@Slf4j
public class ItemStorageImpl implements ItemStorage {
    private final Map<Long, Item> items = new HashMap<>();
    private long id = 1;

    @Override
    public Item create(Item item) {
        item.setId(id);
        items.put(id++, item);
        return item;
    }

    @Override
    public Item update(Item newItem) {
        Item item = items.get(newItem.getId());
        if (newItem.getName() != null) {
            item.setName(newItem.getName());
        }

        if (newItem.getDescription() != null) {
            item.setDescription(newItem.getDescription());
        }

        if (newItem.getAvailable() != null) {
            item.setAvailable(newItem.getAvailable());
        }

        return item;
    }

    @Override
    public Item getItemById(long id) {
        return items.get(id);
    }

    @Override
    public List<Item> getAllItemsByOwner(long ownerId) {
        return items.values().stream()
                .filter(item -> item.getOwnerId() == ownerId)
                .toList();
    }

    @Override
    public List<Item> searchByText(String text) {
        return items.values().stream()
                .filter(item -> item.getDescription().toLowerCase().contains(text) ||
                        item.getName().toLowerCase().contains(text))
                .filter(Item::getAvailable)
                .toList();
    }

    @Override
    public boolean accessVerification(long id, long ownerId) {
        Item item = items.get(id);
        return item.getOwnerId() == ownerId;
    }

    @Override
    public boolean checkItem(long itemId) {
        return items.containsKey(itemId);
    }
}
