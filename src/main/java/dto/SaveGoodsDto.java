package dto;

import jakarta.servlet.http.Part;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
public class SaveGoodsDto {

    private String productName;
    private String description;
    private String price;
    private String quantity;
    private Part imageName;


}
