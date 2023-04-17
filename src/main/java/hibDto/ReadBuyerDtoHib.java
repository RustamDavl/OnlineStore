package hibDto;

import hibEntities.PersonalInfo;
import lombok.Builder;
import lombok.Data;
import lombok.Value;

import java.time.LocalDate;


@Builder
@Value
public class ReadBuyerDtoHib {

    Integer id;
    String email;
    String password;
    PersonalInfo personalInfo;
}
