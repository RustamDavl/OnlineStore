package util;

import hibEntities.Buyer;
import hibEntities.Goods;
import hibEntities.Orders;
import lombok.experimental.UtilityClass;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

@UtilityClass
public class HibernateUtil {

    public static SessionFactory buildSessionFactory() {
        Configuration configuration = new Configuration();

        configuration.addAnnotatedClass(Buyer.class);
        configuration.addAnnotatedClass(Orders.class);
        configuration.addAnnotatedClass(Goods.class);


        configuration.configure();

        return configuration.buildSessionFactory();
    }
}
