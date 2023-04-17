package entities;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class Orders {
    private Integer number;
    private Integer buyerId;
    private Integer amount;
    private String prodName;
}
