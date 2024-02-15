package api.discount.domain.discountPolicy;

import api.discount.domain.ShoppingCartItem;
import api.discount.model.Money;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@DiscriminatorValue("amount")
public class AmountDiscount extends DiscountType {

    private BigDecimal amount;

    public AmountDiscount(BigDecimal amount) {
        this.amount = amount;
    }

    @Override
    public Money getDiscountType(ShoppingCartItem target, Money discountRange) {
        return Money.wons(amount).times(target.getCount());
    }
}
