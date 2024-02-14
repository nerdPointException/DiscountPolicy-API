package api.discount.domain;

import api.discount.model.Money;
import jakarta.persistence.*;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Getter
public class ShoppingCart {

    @Id
    @GeneratedValue
    @Column(name = "shopping_cart_id")
    private Long id;

    private BigDecimal fullPrice = BigDecimal.ZERO;
    @Column(updatable = false)
    private LocalDateTime orderDateTime;

    // ==== 생성자 ==== //
    public ShoppingCart() {
        this.orderDateTime = LocalDateTime.now();
    }

    // ==== 편의 메서드 ==== //
    public void addPrice(Money money) {
        this.fullPrice = this.fullPrice.add(money.getAmount());
    }

    public Money getFullPrice() {
        return Money.wons(this.fullPrice);
    }
}