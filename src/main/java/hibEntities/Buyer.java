package hibEntities;


import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
@Entity
public class Buyer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String email;
    private String password;

    @Embedded
    private PersonalInfo personalInfo;

    @Builder.Default
    @OneToMany(mappedBy = "buyer")
    @ToString.Exclude
    List<Orders> orders = new ArrayList<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Buyer buyer = (Buyer) o;
        return id != null && Objects.equals(id, buyer.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
