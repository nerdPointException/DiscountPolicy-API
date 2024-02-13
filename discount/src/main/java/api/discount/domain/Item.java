package api.discount.domain;

import api.discount.domain.discountPolicy.DiscountPolicy;
import api.discount.model.Money;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Getter
public class Item {

    @Id
    @GeneratedValue
    @Column(name = "item_id")
    private Long id;

    private BigDecimal price;

    public Money getDiscountedPrice() {
        return new Money(price);
    }
}
