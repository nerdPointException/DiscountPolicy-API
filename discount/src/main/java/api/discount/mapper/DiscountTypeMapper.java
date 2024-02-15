package api.discount.mapper;

import api.discount.domain.discountPolicy.AmountDiscount;
import api.discount.domain.discountPolicy.DiscountType;
import api.discount.model.discountPolicyDto.AmountDiscountDto;
import org.springframework.stereotype.Component;

@Component
public class DiscountTypeMapper {

    public DiscountType mapToDiscountType(Object input) {

        if (input instanceof AmountDiscountDto) {
            return mapAmountDiscount((AmountDiscountDto) input);
        }

        throw new IllegalArgumentException("Unsupported type");
    }

    private DiscountType mapAmountDiscount(AmountDiscountDto amount) {
        return new AmountDiscount(amount.getAmount());
    }
}
