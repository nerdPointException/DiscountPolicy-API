package api.discount.domain.discountPolicy;

import api.discount.domain.DiscountableChecker;
import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "dtype")
@Getter
public abstract class DiscountCondition {

    @Id
    @GeneratedValue
    @Column(name = "discount_condition_id")
    private Long id;

    abstract public boolean isSatisfyCondition(DiscountableChecker target);
}
