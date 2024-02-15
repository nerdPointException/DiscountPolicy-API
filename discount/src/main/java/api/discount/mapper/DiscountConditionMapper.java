package api.discount.mapper;

import api.discount.domain.discountPolicy.DiscountCondition;
import api.discount.domain.discountPolicy.discountCondition.SingleItem;
import api.discount.model.discountPolicyDto.BuyNGetMFreeDto;
import api.discount.model.discountPolicyDto.SingleItemDto;
import api.discount.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DiscountConditionMapper {

    private final ItemRepository itemRepository;

    public DiscountCondition mapToDiscountCondition(Object input) {

        if (input instanceof SingleItemDto) {
            return mapSingleItem((SingleItemDto) input);

        } else if (input instanceof BuyNGetMFreeDto) {
            return mapBuyNGetMFree((BuyNGetMFreeDto) input);

        }
        throw new IllegalArgumentException("Unsupported type");

    }

    private DiscountCondition mapSingleItem(SingleItemDto singleItem) {
        return new SingleItem(itemRepository.findById(singleItem.getItemId())
                .orElse(null));
    }

    private DiscountCondition mapBuyNGetMFree(BuyNGetMFreeDto buyNGetMFree) {
        return null;
    }

}
