package api.discount.domain.discountPolicy;

import api.discount.domain.DiscountableChecker;
import api.discount.domain.Item;
import api.discount.model.Money;
import jakarta.persistence.*;
import lombok.Getter;

import java.math.BigDecimal;

@Entity
@DiscriminatorValue("amount")
@Getter
public class AmountDiscount extends DiscountType {

    private BigDecimal amount;

    @OneToOne
    @JoinColumn(name = "item_id")
    private Item item;

    @Override
    public Money discountedPrice(DiscountableChecker target) {
        return null;
    }
}
