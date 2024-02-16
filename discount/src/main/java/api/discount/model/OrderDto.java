package api.discount.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@ToString
@Getter @Setter
public class OrderDto {

    private List<ItemInfo> itemsInfo;

}
