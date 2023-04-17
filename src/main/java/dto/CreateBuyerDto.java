package dto;

import lombok.Builder;
import lombok.Data;
import lombok.Value;

@Value
@Builder
public class CreateBuyerDto {

    String name;
    String lastName;
    String email;
    String password;
    String phone;
    String address;
    String birthday;
    String role;


}
