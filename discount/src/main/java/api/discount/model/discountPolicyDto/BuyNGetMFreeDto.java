package api.discount.model.discountPolicyDto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter @Setter
public class BuyNGetMFreeDto extends DiscountConditionDto {
    private long itemId;
    private int buyAmount;
    private int freeAmount;
}
