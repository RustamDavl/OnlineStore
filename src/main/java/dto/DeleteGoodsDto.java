package dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DeleteGoodsDto {
    private String productName;
}
