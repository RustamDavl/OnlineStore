package mapper;

import hibDto.ShowGoodsDtoHib;
import hibEntities.Goods;

public class ShowGoodsDtoHibMapper implements Mapper<Goods, ShowGoodsDtoHib>{
    @Override
    public ShowGoodsDtoHib mapFrom(Goods goods) {
        return ShowGoodsDtoHib.builder()
                .productInfo(goods.getProductInfo())
                .imagePath(goods.getPathToImage())
                .build();
    }
}
