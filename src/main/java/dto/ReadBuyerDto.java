package dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class ReadBuyerDto {

    private Integer id;
    private String name;
    private String lastName;
    private String email;
    private String password;
    private String phone;
    private String address;
    private LocalDate birthday;
}
