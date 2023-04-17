package hibDto;

import hibEntities.ProductInfo;
import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class ShowGoodsDtoHib {
    ProductInfo productInfo;
    String imagePath;
}
