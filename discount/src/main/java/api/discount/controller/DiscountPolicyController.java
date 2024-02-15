package api.discount.controller;

import api.discount.domain.discountPolicy.DiscountCondition;
import api.discount.domain.discountPolicy.DiscountType;
import api.discount.mapper.DiscountConditionMapper;
import api.discount.mapper.DiscountTypeMapper;
import api.discount.model.DiscountPolicyDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/v1/discount-policy")
@RequiredArgsConstructor
public class DiscountPolicyController {

    private final DiscountConditionMapper discountConditionMapper;
    private final DiscountTypeMapper discountTypeMapper;

    @PostMapping
    public String save(@RequestBody DiscountPolicyDto discountPolicyDto) {

        DiscountCondition discountCondition = discountConditionMapper.mapToDiscountCondition(discountPolicyDto.getDiscountCondition());
        DiscountType discountType = discountTypeMapper.mapToDiscountType(discountPolicyDto.getDiscountType());

        log.info("DiscountPolicyData = {}", discountPolicyDto.toString());
        log.info("condition = {} , type = {}", discountCondition, discountType);

        return "ok";
    }
}
