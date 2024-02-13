package api.discount.domain;

import api.discount.domain.discountPolicy.DiscountPolicy;
import api.discount.model.Money;
import jakarta.persistence.*;
import lombok.Getter;

import java.util.List;

@Entity
@Getter
public class ShoppingCartItem implements DiscountableChecker {

    @Id
    @GeneratedValue
    @Column(name = "shopping_cart_item_id")
    private Long id;

    @OneToOne
    @JoinColumn(name = "item_id")
    private Item item;
    private int count;

    @Override
    public List<DiscountPolicy> hasDiscountPolicy() {
        return null;
    }

    public Money getItemsPrice() {
        Money discountedAmount = Money.ZERO;

        for (DiscountPolicy discountPolicy : hasDiscountPolicy()) {
            discountedAmount = discountPolicy.discountedAmount(this);
        }
        return item.getDiscountedPrice().times(count).minus(discountedAmount);
    }
}
