package services;

import org.hibernate.SessionFactory;
import util.HibernateUtil;

public class BaseService {
    protected final SessionFactory sessionFactory = HibernateUtil.buildSessionFactory();
}
