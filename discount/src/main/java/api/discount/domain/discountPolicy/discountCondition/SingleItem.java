package api.discount.domain.discountPolicy.discountCondition;

import api.discount.domain.DiscountableChecker;
import api.discount.domain.Item;
import api.discount.domain.discountPolicy.DiscountCondition;
import jakarta.persistence.*;
import lombok.Getter;

import java.util.Objects;

@Entity
@DiscriminatorValue("single_item")
@Getter
public class SingleItem extends DiscountCondition {

    @OneToOne
    @JoinColumn(name = "item_id")
    private Item item;

    @Override
    public boolean isSatisfyCondition(DiscountableChecker target) {
        Item targetItem = (Item) target;
        return Objects.equals(item.getId(), targetItem.getId());
    }
}
