package hibEntities;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.Objects;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Orders {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer number;

    @ManyToOne
    @JoinColumn(name = "buyer_id")
    private Buyer buyer;

    @Embedded
    private Good goods;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Orders orders = (Orders) o;
        return number != null && Objects.equals(number, orders.number);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
