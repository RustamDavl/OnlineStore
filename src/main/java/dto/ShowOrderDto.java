package dto;


import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class ShowOrderDto {

    Integer number;
    String goodsAndAmount;
}
