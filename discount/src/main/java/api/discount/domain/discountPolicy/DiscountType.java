package api.discount.domain.discountPolicy;

import api.discount.domain.ShoppingCartItem;
import api.discount.model.Money;
import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "dtype")
@Getter
abstract public class DiscountType {

    @Id
    @GeneratedValue
    @Column(name = "discount_type_id")
    private Long id;

    public abstract Money getDiscountType(ShoppingCartItem target, Money discountRange);
}
