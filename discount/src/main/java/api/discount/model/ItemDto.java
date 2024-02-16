package api.discount.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;

@ToString
@Getter @Setter
public class ItemDto {

    private String name;
    private BigDecimal price;
    private int stockQuantity;

}
