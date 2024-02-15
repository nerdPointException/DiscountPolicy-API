package api.discount.model.discountPolicyDto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;

@ToString
@Getter @Setter
public class AmountDiscountDto extends DiscountTypeDto {
    private BigDecimal amount;
}
