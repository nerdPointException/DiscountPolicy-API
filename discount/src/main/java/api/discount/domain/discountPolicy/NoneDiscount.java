package api.discount.domain.discountPolicy;

import api.discount.domain.OrderItem;
import api.discount.model.Money;

public class NoneDiscount extends DiscountPolicy {

    @Override
    public Money getDiscountAmount(OrderItem target) {
        return Money.ZERO;
    }
}
