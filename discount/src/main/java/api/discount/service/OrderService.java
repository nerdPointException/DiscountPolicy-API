package api.discount.service;

import api.discount.domain.Item;
import api.discount.domain.Order;
import api.discount.domain.OrderItem;
import api.discount.domain.discountPolicy.Discount;
import api.discount.model.ItemInfo;
import api.discount.model.Money;
import api.discount.model.OrderDto;
import api.discount.repository.ItemRepository;
import api.discount.repository.OrderItemRepository;
import api.discount.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;

    private final ItemRepository itemRepository;
    private final DiscountPolicyService discountPolicyService;


    @Transactional
    public void order(List<ItemInfo> itemsInfo) {
        Order order = orderRepository.save(new Order());

        List<Item> items = new ArrayList<>();
        Money discountedMoney = Money.ZERO;

        for (ItemInfo info : itemsInfo) {
            Item findItem = itemRepository.findById(info.getItemId()).orElse(null);

            OrderItem orderItem = orderItemRepository.save(
                                            new OrderItem(order, findItem, findItem.getPrice(), info.getCount()));

            discountedMoney = discountedMoney.plus(calculateDiscountedAmount(orderItem));
        }

        log.info("FullPrice = {}", order.getFullPrice().getAmount().toString());
        log.info("discountedAmount = {}", discountedMoney.getAmount().toString());

        log.info("FinalPrice = {}", order.getFullPrice().minus(discountedMoney).getAmount().toString());
    }

    private Money calculateDiscountedAmount(OrderItem orderItem) {
        List<Discount> discountPolicy =discountPolicyService.findDiscountPolicyByItemId(orderItem.getItem().getId());
        return discountPolicy.get(0).getDiscountAmount(orderItem);
    }

}
