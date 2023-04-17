package repository;

import com.querydsl.core.types.Predicate;
import com.querydsl.jpa.impl.JPAQuery;
import hibEntities.Buyer;
import hibEntities.QBuyer;
import org.hibernate.Session;

import java.util.Optional;

import static hibEntities.QBuyer.*;

public class BuyerRepository extends BaseRepository<Integer, Buyer> {


    public BuyerRepository(Session session) {
        super(session, Buyer.class);
    }

    public Optional<Buyer> findByEmailAndPassword(String email, String password) {



       var buyerList =  new JPAQuery<Buyer>(super.getSession())
                .select(buyer)
                .from(buyer)
                .where(buyer.email.eq(email).and(buyer.password.eq(password)))
                .fetch();
//        var  buyerList = super.getSession().createQuery("""
//                        select b from Buyer b where b.email = :email and b.password = :password
//                        """, Buyer.class).setParameter("email", email)
//                .setParameter("password", password)
//                .getResultList();
        return buyerList.stream().findFirst();


    }


}
