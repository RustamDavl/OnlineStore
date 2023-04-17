package dto;

import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class ShowBuyerDto {

    Integer id;
    String name;
    String lastName;
    String email;
    String phone;

}
