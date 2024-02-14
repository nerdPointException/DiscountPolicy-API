package api.discount.controller;

import api.discount.domain.Item;
import api.discount.domain.ShoppingCart;
import api.discount.domain.ShoppingCartItem;
import api.discount.domain.discountPolicy.AmountDiscount;
import api.discount.domain.discountPolicy.Discount;
import api.discount.domain.discountPolicy.discountCondition.SingleItem;
import api.discount.repository.DiscountPolicyRepository;
import api.discount.repository.ItemRepository;
import api.discount.repository.ShoppingCartItemRepository;
import api.discount.repository.ShoppingCartRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Slf4j
@Controller
@RequiredArgsConstructor
public class PostController {

    private final ItemRepository itemRepository;
    private final ShoppingCartRepository shoppingCartRepository;
    private final DiscountPolicyRepository discountPolicyRepository;
    private final ShoppingCartItemRepository shoppingCartItemRepository;

    @PostConstruct
    @Transactional
    public void saveItem() {
        Item saveItem = new Item();
        saveItem.setName("향수");
        saveItem.setPrice(BigDecimal.valueOf(10000));
        itemRepository.save(saveItem);

        ShoppingCart saveShoppingCart = new ShoppingCart();
        shoppingCartRepository.save(saveShoppingCart);

        ShoppingCartItem saveShoppingCartItem = ShoppingCartItem.builder()
                                                    .shoppingCart(saveShoppingCart)
                                                    .item(saveItem)
                                                    .orderPrice(saveItem.getPrice())
                                                    .count(3)
                                                    .build();
        shoppingCartItemRepository.save(saveShoppingCartItem);

        // ==== 2번째 저장 ==== //
        Item saveItem2 = new Item();
        saveItem2.setName("향수");
        saveItem2.setPrice(BigDecimal.valueOf(20000));
        itemRepository.save(saveItem2);

        ShoppingCart saveShoppingCart2 = new ShoppingCart();
        shoppingCartRepository.save(saveShoppingCart2);

        ShoppingCartItem saveShoppingCartItem2 = ShoppingCartItem.builder()
                .shoppingCart(saveShoppingCart)
                .item(saveItem2)
                .orderPrice(saveItem2.getPrice())
                .count(1)
                .build();
        shoppingCartItemRepository.save(saveShoppingCartItem2);

        // ==== 컨디션 ==== //

        SingleItem condition = new SingleItem();
        condition.setItem(saveItem);
        AmountDiscount type = new AmountDiscount();
        type.setAmount(BigDecimal.valueOf(1000));

        Discount discountPolicy = new Discount();
        discountPolicy.setPolicyName("설특가 할인");
        discountPolicy.setDiscountCondition(condition);
        discountPolicy.setDiscountType(type);
        discountPolicyRepository.save(discountPolicy);


        log.info("할인금액 = {}", discountPolicy.getDiscountAmount(saveShoppingCartItem).getAmount().toString());
        log.info(saveShoppingCart.getFullPrice().minus(discountPolicy.getDiscountAmount(saveShoppingCartItem)).getAmount().toString());
    }
}
