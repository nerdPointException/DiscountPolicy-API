package api.discount.domain;

import api.discount.exception.StockQuantityException;
import api.discount.model.Money;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Item {

    @Id
    @GeneratedValue
    @Column(name = "item_id")
    private Long id;

    private String name;
    private BigDecimal price;
    private int stockQuantity;

    // ==== 생성자 ==== //
    @Builder
    public Item(String name, Money price, int stockQuantity) {
        this.name = name;
        this.price = price.getAmount();
        this.stockQuantity = stockQuantity;
    }

    // ==== 접근자 ==== //
    public Money getPrice() {
        return Money.wons(price);
    }

    public void updateItem(String name, Money price, int stockQuantity) {
        this.name = name;
        this.price = price.getAmount();
        this.stockQuantity = stockQuantity;
    }

    // ==== 편의 메서드 ==== //

    /**
     * 재고 수량을 증가시킵니다.
     * @param count 증가할 수량
     */
    public void addQuantity(int count) {
        this.stockQuantity += count;
    }

    /**
     * 재고 수량을 감소시킵니다.
     * @param count 감소할 수량
     */
    public void minusQuantity(int count) {
        if (this.stockQuantity - count < 0) {
            throw new StockQuantityException("재고가 부족합니다.");
        }
        this.stockQuantity -= count;
    }

}
