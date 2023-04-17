package dto;

import lombok.Builder;
import lombok.Value;

import java.time.LocalDate;

@Value
@Builder
public class ReadAdminDto {

    Integer id;
    String name;
    String lastName;
    String email;
    String password;
    String phone;
    String address;
    LocalDate birthday;
}
