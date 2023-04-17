package dto;

import lombok.Builder;
import lombok.Data;
import lombok.Value;

@Builder
@Value
public class BuyGoodsDto {
     String prodName;
     String amount;

}
