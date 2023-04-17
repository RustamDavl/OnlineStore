package entities;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Builder
@Data
public class Goods {

    private Integer id;
    private String productName;
    private String description;
    private BigDecimal price;
    private Integer remainingQuantity;
    private Integer ordersId;
    private String pathToImage;


}
