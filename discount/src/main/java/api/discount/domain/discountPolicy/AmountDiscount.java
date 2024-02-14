package api.discount.domain.discountPolicy;

import api.discount.domain.Item;
import api.discount.domain.ShoppingCartItem;
import api.discount.model.Money;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@DiscriminatorValue("amount")
@Getter @Setter
public class AmountDiscount extends DiscountType {

    private BigDecimal amount;

    @Override
    public Money getDiscountType(ShoppingCartItem target) {
        return Money.wons(amount).times(target.getCount());
    }
}
