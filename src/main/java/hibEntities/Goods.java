package hibEntities;


import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Goods {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Embedded
    private ProductInfo productInfo;

    private BigDecimal price;

    @Column(name = "remaining_quantity")
    private Integer remainingQuantity;

    @Column(name = "image")
    private String pathToImage;
}
