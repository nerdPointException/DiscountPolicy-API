package api.discount.domain.discountPolicy;

import api.discount.domain.DiscountableChecker;
import api.discount.model.Money;

public class PercentDiscount extends DiscountType{
    @Override
    public Money discountedPrice(DiscountableChecker target) {
        return null;
    }
}
