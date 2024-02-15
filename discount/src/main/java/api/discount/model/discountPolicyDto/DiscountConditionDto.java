package api.discount.model.discountPolicyDto;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = SingleItemDto.class, name = "SingleItem"),
        @JsonSubTypes.Type(value = BuyNGetMFreeDto.class, name = "BuyN")
})
abstract public class DiscountConditionDto {
    public abstract String toString();
}
