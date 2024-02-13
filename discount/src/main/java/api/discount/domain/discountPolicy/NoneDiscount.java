package api.discount.domain.discountPolicy;

import api.discount.domain.DiscountableChecker;
import api.discount.model.Money;

public class NoneDiscount extends DiscountPolicy {
    @Override
    public Money discountedAmount(DiscountableChecker target) {
        return Money.ZERO;
    }
}
