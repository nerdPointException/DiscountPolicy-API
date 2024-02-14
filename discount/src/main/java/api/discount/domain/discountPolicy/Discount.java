package api.discount.domain.discountPolicy;

import api.discount.domain.ShoppingCartItem;
import api.discount.model.Money;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import static jakarta.persistence.FetchType.LAZY;

@Entity
@Getter @Setter
public class Discount extends DiscountPolicy {

    @Id
    @GeneratedValue
    @Column(name = "discount_id")
    private Long id;

    private String policyName;

    @OneToOne(fetch = LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "discount_condition_id")
    private DiscountCondition discountCondition;

    @OneToOne(fetch = LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "discount_type_id")
    private DiscountType discountType;

    // ==== 비즈니스 로직 ==== //
    @Override
    public Money getDiscountAmount(ShoppingCartItem target) {

        Money discountAmount = Money.ZERO;

        if (discountCondition.isSatisfying(target)) {
            Money discountRange = discountCondition.decideDiscountRange(target);
            return discountType.getDiscountType(target);
        }

        return discountAmount;
    }
}
