package api.discount.domain.discountPolicy;

import api.discount.domain.ShoppingCartItem;
import api.discount.model.Money;

public class NoneDiscount extends DiscountPolicy {

    @Override
    public Money getDiscountAmount(ShoppingCartItem target) {
        return Money.ZERO;
    }
}
