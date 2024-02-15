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

    /**
     * 전체 금액의 값을 조정합니다.
     * @param changeAmount 변경할 돈의 금액
     */
    public void changePrice(Money changeAmount) {
        this.fullPrice = this.fullPrice.add(changeAmount.getAmount());
    }

    /**
     * 전체 금액의 값을 조회합니다.
     * @return 장바구니의 전체 가격
     */
    public Money getFullPrice() {
        return Money.wons(this.fullPrice);
    }
}