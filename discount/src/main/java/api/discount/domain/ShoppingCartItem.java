package api.discount.domain;

import api.discount.model.Money;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

import static jakarta.persistence.FetchType.LAZY;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ShoppingCartItem {

    @Id
    @GeneratedValue
    @Column(name = "shopping_cart_item_id")
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "shopping_cart_id")
    private ShoppingCart shoppingCart;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "item_id")
    private Item item;

    private BigDecimal orderPrice;
    private int count;

    // ==== 생성자 ==== //
    @Builder
    public ShoppingCartItem(ShoppingCart shoppingCart, Item item, Money orderPrice, int count) {
        this.shoppingCart = shoppingCart;
        this.item = item;
        this.count = count;
        this.orderPrice = orderPrice.getAmount();

        Money addMoney = getOrderPrice(orderPrice);
        shoppingCart.addPrice(addMoney);
    }

    // ==== 편의 메서드 ==== //
    private Money getOrderPrice(Money orderPrice) {
        return orderPrice.times(count);
    }

}
