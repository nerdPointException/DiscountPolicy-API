package api.discount.controller;

import api.discount.domain.discountPolicy.Discount;
import api.discount.domain.discountPolicy.DiscountCondition;
import api.discount.domain.discountPolicy.DiscountType;
import api.discount.mapper.DiscountConditionMapper;
import api.discount.mapper.DiscountTypeMapper;
import api.discount.model.DiscountPolicyDto;
import api.discount.service.DiscountPolicyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/v1/discount-policy")
@RequiredArgsConstructor
public class DiscountPolicyController {

    private final DiscountPolicyService discountPolicyService;

    private final DiscountTypeMapper discountTypeMapper;
    private final DiscountConditionMapper discountConditionMapper;

    @PostMapping
    public String save(@RequestBody DiscountPolicyDto discountPolicyDto) {

        DiscountType discountType = discountTypeMapper.mapToDiscountType(discountPolicyDto.getDiscountType());
        DiscountCondition discountCondition = discountConditionMapper.mapToDiscountCondition(discountPolicyDto.getDiscountCondition());

        discountPolicyService.saveDiscountPolicy(discountPolicyDto.getPolicyName(), discountCondition, discountType);

        log.info("DiscountPolicyData = {}", discountPolicyDto.toString());
        log.info("condition = {} , type = {}", discountCondition, discountType);

        return "ok";
    }

    @GetMapping("/item/{itemId}")
    public List<Discount> findDiscountPolicyAboutItem(@PathVariable(name = "itemId") Long itemId) {
        return discountPolicyService.findDiscountPolicyByItemId(itemId);
    }
}
