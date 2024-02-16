package api.discount.domain.discountPolicy;

import api.discount.domain.OrderItem;
import api.discount.model.Money;

abstract public class DiscountPolicy {
    // ==== 비즈니스 로직 ==== //
    public abstract Money getDiscountAmount(OrderItem target);
}
