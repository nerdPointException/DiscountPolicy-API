package api.discount.domain;

import api.discount.domain.discountPolicy.DiscountPolicy;

import java.util.List;

public interface DiscountableChecker {
    abstract List<DiscountPolicy> hasDiscountPolicy();
}
