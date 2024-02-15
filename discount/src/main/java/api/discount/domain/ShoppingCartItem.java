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

    private BigDecimal singleItemPrice;
    private int count;

    // ==== 생성자 ==== //
    @Builder
    public ShoppingCartItem(ShoppingCart shoppingCart, Item item, Money singleItemPrice, int count) {
        this.shoppingCart = shoppingCart;
        this.item = item;
        this.count = count;
        this.singleItemPrice = singleItemPrice.getAmount();

        item.minusQuantity(count);
        Money addMoney = getOrderPrice(singleItemPrice);
        shoppingCart.changePrice(addMoney);
    }

    // ==== 편의 메서드 ==== //

    /**
     * 단일 품목에 구매 갯수만큼 곱합니다.
     * @param singleItemPrice 단일품목 가격
     * @return 구매할 상품 묶음의 값
     */
    private Money getOrderPrice(Money singleItemPrice) {
        return singleItemPrice.times(count);
    }

    /**
     * ShoppingCart의 값을 재설정합니다.
     * @param updateCount 변경된 구매 갯수
     */
    public void updateShoppingCartItem(int updateCount) {

        Money beforeMoney = getOrderPrice(Money.wons(this.singleItemPrice));
        updateCount(updateCount);
        Money afterMoney = getOrderPrice(Money.wons(this.singleItemPrice));

        Money diff = beforeMoney.minus(afterMoney);
        shoppingCart.changePrice(diff);
    }

    /**
     * 구매 갯수를 변경하고 변경된 갯수만큼 재고를 수정합니다.
     * @param updateCount 변경된 구매 갯수
     */
    private void updateCount(int updateCount) {
        int beforeCount = this.count;
        int diff = Math.abs(beforeCount - updateCount);
        this.count = updateCount;

        if (beforeCount > updateCount) {
            item.addQuantity(diff);
        } else if (beforeCount < updateCount) {
            item.minusQuantity(diff);
        }
    }

}
