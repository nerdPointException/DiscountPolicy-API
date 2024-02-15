package api.discount.domain.discountPolicy;

import api.discount.domain.ShoppingCartItem;
import api.discount.model.Money;

public class PercentDiscount extends DiscountType{

    @Override
    public Money getDiscountType(ShoppingCartItem target, Money discountRange) {
        return null;
    }
}
