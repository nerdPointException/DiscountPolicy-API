package api.discount.domain.discountPolicy;

import api.discount.domain.OrderItem;
import api.discount.model.Money;

public class PercentDiscount extends DiscountType{

    @Override
    public Money getDiscountType(OrderItem target, Money discountRange) {
        return null;
    }
}
