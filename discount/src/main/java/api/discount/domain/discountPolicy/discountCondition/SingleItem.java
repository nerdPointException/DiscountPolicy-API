package api.discount.domain.discountPolicy.discountCondition;

import api.discount.domain.Item;
import api.discount.domain.ShoppingCartItem;
import api.discount.domain.discountPolicy.DiscountCondition;
import api.discount.model.Money;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

import static jakarta.persistence.FetchType.LAZY;

@Entity
@DiscriminatorValue("single_item")
@Getter @Setter
public class SingleItem extends DiscountCondition {

    @OneToOne(fetch = LAZY)
    @JoinColumn(name = "item_id")
    private Item item;


    @Override
    protected boolean isSatisfying(ShoppingCartItem target) {
        return Objects.equals(target.getItem().getId(), item.getId());
    }

    @Override
    public Money decideDiscountRange(ShoppingCartItem target) {
        return target.getItem().getPrice();
    }

}
