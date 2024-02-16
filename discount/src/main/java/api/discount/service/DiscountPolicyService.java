package api.discount.service;

import api.discount.domain.Item;
import api.discount.domain.discountPolicy.Discount;
import api.discount.domain.discountPolicy.DiscountCondition;
import api.discount.domain.discountPolicy.DiscountType;
import api.discount.repository.DiscountPolicyRepository;
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
public class DiscountPolicyService {

    private final DiscountPolicyRepository discountPolicyRepository;

    private final ItemService itemService;

    @Transactional
    public void saveDiscountPolicy(String policyName, DiscountCondition discountCondition,
                                   DiscountType discountType) {

        discountPolicyRepository.save(Discount.builder()
                                        .policyName(policyName)
                                        .discountCondition(discountCondition)
                                        .discountType(discountType)
                                        .build());
    }

    public List<Discount> findDiscountPolicyByItemId(Long itemId) {

        Item savedItem = itemService.findOne(itemId);

        return discountPolicyRepository.findDiscountPolicyByItem(savedItem);
    }

}
