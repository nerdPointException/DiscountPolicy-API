package api.discount.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter @Setter
public class ItemInfo {
    private Long itemId;
    private Integer count;
}
