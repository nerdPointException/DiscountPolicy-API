package api.discount.model.discountPolicyDto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter @Setter
public class SingleItemDto extends DiscountConditionDto {
    private Long itemId;
}
