package api.discount.model;

import api.discount.model.discountPolicyDto.DiscountConditionDto;
import api.discount.model.discountPolicyDto.DiscountTypeDto;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter @Setter
public class DiscountPolicyDto {

    private String policyName;
    private DiscountConditionDto discountCondition;
    private DiscountTypeDto discountType;

}
