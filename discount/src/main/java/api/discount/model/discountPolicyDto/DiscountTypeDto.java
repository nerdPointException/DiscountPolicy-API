package api.discount.model.discountPolicyDto;

import api.discount.domain.discountPolicy.AmountDiscount;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.ToString;

@ToString
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = AmountDiscountDto.class, name = "Amount")
})
abstract public class DiscountTypeDto {
    public abstract String toString();
}
