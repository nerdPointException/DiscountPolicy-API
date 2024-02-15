package api.discount.domain.discountPolicy;

import api.discount.domain.ShoppingCartItem;
import api.discount.model.Money;
import jakarta.persistence.*;
import lombok.*;

import static jakarta.persistence.FetchType.LAZY;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
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

    // ==== 생성자 ==== //
    @Builder
    public Discount(String policyName, DiscountCondition discountCondition, DiscountType discountType) {
        this.policyName = policyName;
        this.discountCondition = discountCondition;
        this.discountType = discountType;
    }

    // ==== 비즈니스 로직 ==== //
    @Override
    public Money getDiscountAmount(ShoppingCartItem target) {

        Money discountAmount = Money.ZERO;

        if (discountCondition.isSatisfying(target)) {
            Money discountRange = discountCondition.decideDiscountRange(target);
            return discountType.getDiscountType(target, discountRange);
        }

        return discountAmount;
    }
}
