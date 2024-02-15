package api.discount.service;

import api.discount.domain.Item;
import api.discount.model.Money;
import api.discount.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;

    @Transactional
    public void saveItem(String name, Money price, int stockQuantity) {
        Item item = Item.builder()
                .name(name)
                .price(price)
                .stockQuantity(stockQuantity)
                .build();

        itemRepository.save(item);
    }

    @Transactional
    public void updateItem(Long itemId, String name, Money price, int stockQuantity) {
        Item savedItem = itemRepository.findById(itemId)
                .orElse(null);
        // ---- 검증 로직 생략 ---- //
        savedItem.updateItem(name, price, stockQuantity);
    }

    private Item findOne(Long itemId) {
        return itemRepository.findById(itemId)
                .orElse(null);
    }

    private List<Item> findAll() {
        return itemRepository.findAll()
                .stream().toList();
    }
}
