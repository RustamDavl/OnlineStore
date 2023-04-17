package repository;

import com.querydsl.core.types.Predicate;
import com.querydsl.jpa.impl.JPAQuery;
import dto.GoodsFilterTwo;
import hibEntities.Goods;
import hibEntities.QGoods;
import org.hibernate.Session;

import java.util.List;
import java.util.Optional;

import static hibEntities.QGoods.*;

public class GoodsRepository extends BaseRepository<Integer, Goods>{
    public GoodsRepository(Session session) {
        super(session, Goods.class);
    }

    public Optional<Goods> getDescriptionByProdName(String prodName) {

        return Optional.ofNullable(new JPAQuery<Goods>(super.getSession())
                 .select(goods)
                 .from(goods)
                 .where(goods.productInfo.name.eq(prodName))
                 .fetchOne());
    }

    public List<Goods> findWithLimitCondition(int limit) {

        return new JPAQuery<Goods>(super.getSession())
                .select(goods)
                .from(goods)
                .limit(limit)
                .fetch();
    }
}
