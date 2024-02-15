package api.discount.service;

import api.discount.repository.ItemRepository;
import api.discount.repository.ShoppingCartItemRepository;
import api.discount.repository.ShoppingCartRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ShoppingCartService {

    private final ItemRepository itemRepository;
    private final DiscountPolicyService discountPolicyService;
    private final ShoppingCartRepository shoppingCartRepository;
    private final ShoppingCartItemRepository shoppingCartItemRepository;



}
