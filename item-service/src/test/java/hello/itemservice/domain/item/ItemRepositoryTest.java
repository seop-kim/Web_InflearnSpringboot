package hello.itemservice.domain.item;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class ItemRepositoryTest {

    ItemRepository itemRepository = new ItemRepository();

    @AfterEach
    public void afterEach() {
        itemRepository.clearStore();
    }

    @Test
    public void save() throws Exception {
        // given
        Item item = new Item("itemA", 10000, 10);

        // when
        Item save = itemRepository.save(item);
        // then
        Item byId = itemRepository.findById(item.getId());
        assertThat(byId).isEqualTo(save);
    }

    @Test
    public void findAll() throws Exception {
        // given
        Item itemA = new Item("itemA", 10000, 10);
        Item itemB = new Item("itemB", 10000, 10);

        itemRepository.save(itemA);
        itemRepository.save(itemB);

        // when
        List<Item> result = itemRepository.findAll();

        // then
        assertThat(result.size()).isEqualTo(2);
        assertThat(result).contains(itemA, itemB);

    }

    @Test
    public void updateItem() throws Exception {
        // given
        Item item = new Item("itemA", 10000, 10);
        Item savedItem = itemRepository.save(item);

        Long itemId = savedItem.getId();

        // when
        Item updateItem = new Item("updateItem", 20000, 30);
        itemRepository.update(itemId, updateItem);

        // then
        Item findItem = itemRepository.findById(itemId);
        assertThat(findItem.getItemName()).isEqualTo(updateItem.getItemName());
        assertThat(findItem.getPrice()).isEqualTo(updateItem.getPrice());
    }


}