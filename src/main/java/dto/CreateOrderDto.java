package dto;


import lombok.Builder;
import lombok.Data;
import lombok.Value;

@Builder
@Value
public class CreateOrderDto {

     Integer buyerId;
     String amount;
     String prodName;
}
