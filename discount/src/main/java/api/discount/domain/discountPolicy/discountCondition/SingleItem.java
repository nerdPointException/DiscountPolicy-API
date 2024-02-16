package api.discount.domain.discountPolicy.discountCondition;

import api.discount.domain.Item;
import api.discount.domain.OrderItem;
import api.discount.domain.discountPolicy.DiscountCondition;
import api.discount.model.Money;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Objects;

import static jakarta.persistence.FetchType.LAZY;

@Getter
@Entity
@DiscriminatorValue("single_item")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SingleItem extends DiscountCondition {

    @OneToOne(fetch = LAZY)
    @JoinColumn(name = "item_id")
    private Item item;

    public SingleItem(Item item) {
        this.item = item;
    }

    @Override
    protected boolean isSatisfying(OrderItem target) {
        return Objects.equals(target.getItem().getId(), item.getId());
    }

    @Override
    public Money decideDiscountRange(OrderItem target) {
        return target.getItem().getPrice();
    }

}
