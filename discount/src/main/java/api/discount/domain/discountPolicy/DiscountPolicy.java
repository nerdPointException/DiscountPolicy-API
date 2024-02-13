package api.discount.domain.discountPolicy;

import api.discount.domain.DiscountableChecker;
import api.discount.model.Money;

abstract public class DiscountPolicy {
    public abstract Money discountedAmount(DiscountableChecker target);
}
