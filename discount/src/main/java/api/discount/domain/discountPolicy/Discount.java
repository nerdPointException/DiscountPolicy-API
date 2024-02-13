package api.discount.domain.discountPolicy;

import api.discount.domain.DiscountableChecker;
import api.discount.domain.Item;
import api.discount.model.Money;
import jakarta.persistence.*;

@Entity
public class Discount extends DiscountPolicy {

    @Id
    @GeneratedValue
    @Column(name = "discount_id")
    private Long id;

    @OneToOne
    @JoinColumn(name = "discount_condition_id")
    private DiscountCondition discountCondition;
    @OneToOne
    @JoinColumn(name = "discount_type_id")
    private DiscountType discountType;

    @Override
    public Money discountedAmount(DiscountableChecker target) {
        if (discountCondition.isSatisfyCondition(target)) {
            return discountType.discountedPrice(target);
        }
        return Money.ZERO;
    }
}
