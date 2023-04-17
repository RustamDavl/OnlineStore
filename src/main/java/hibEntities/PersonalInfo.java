package hibEntities;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.time.LocalDate;

@Embeddable
public class PersonalInfo {

    private String name;
    @Column(name = "last_name")
    private String lastName;
    private String phone;
    private String address;
    private LocalDate birthday;


}
