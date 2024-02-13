package api.discount.domain;

import api.discount.domain.discountPolicy.DiscountPolicy;
import api.discount.model.Money;
import jakarta.persistence.*;
import lombok.Getter;

import java.util.List;

@Entity
@Getter
public class ShoppingCart implements DiscountableChecker {

    @Id
    @GeneratedValue
    @Column(name = "shopping_cart_id")
    private Long id;

    @OneToMany
    @JoinColumn(name = "shopping_cart_item_id")
    private List<ShoppingCartItem> shoppingCartItemList;

    public Money getFullDiscountedPrice() {
        Money fullPrice = Money.ZERO;

        for (ShoppingCartItem shoppingCartItem : shoppingCartItemList) {
            fullPrice.plus(shoppingCartItem.getItemsPrice());
        }
        Money discountedAmount = Money.ZERO;

        for (DiscountPolicy discountPolicy : hasDiscountPolicy()) {
            discountedAmount = discountPolicy.discountedAmount(this);
        }

        return fullPrice.minus(discountedAmount);
    }
    @Override
    public List<DiscountPolicy> hasDiscountPolicy() {
        return null;
    }
}
