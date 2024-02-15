package api.discount.service;

import api.discount.domain.discountPolicy.Discount;
import api.discount.domain.discountPolicy.DiscountCondition;
import api.discount.domain.discountPolicy.DiscountType;
import api.discount.repository.DiscountPolicyRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class DiscountPolicyService {

    private final DiscountPolicyRepository discountPolicyRepository;

    @Transactional
    public void saveDiscountPolicy(String policyName, DiscountCondition discountCondition,
                                   DiscountType discountType) {

        discountPolicyRepository.save(Discount.builder()
                                        .policyName(policyName)
                                        .discountCondition(discountCondition)
                                        .discountType(discountType)
                                        .build());
    }

}
