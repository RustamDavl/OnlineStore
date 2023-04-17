package com.rustdv;

import com.querydsl.core.types.Predicate;
import com.querydsl.jpa.impl.JPAQuery;
import hibEntities.Buyer;
import hibEntities.QBuyer;
import lombok.Builder;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.*;
import repository.BuyerRepository;
import services.BuyerService;
import util.HibernateUtil;

import static hibEntities.QBuyer.buyer;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class BuyerRepositoryTest {

    private final SessionFactory factory = HibernateUtil.buildSessionFactory();





    @AfterAll
    void afterAll() {

        factory.close();
    }

    @Test
    void findBuyerByEmailAndPasswordUsingQueryDsl() {


        var session = factory.getCurrentSession();
        session.getTransaction();




        session.getTransaction().commit();

    }

    @Test
    void findBuyerByEmailAndPassword() {


//        var list = buyerService.login2("easton12345@gmail.com", "qwerty");
//

        //Assertions.assertTrue(list.isPresent());

    }
}
