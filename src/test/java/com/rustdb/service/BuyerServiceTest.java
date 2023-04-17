package com.rustdb.service;


import com.querydsl.core.types.Predicate;
import com.querydsl.jpa.impl.JPAQuery;
import hibEntities.Buyer;
import hibEntities.Goods;
import hibEntities.QBuyer;
import hibEntities.QGoods;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.*;
import repository.BuyerRepository;
import services.BuyerService;
import util.HibernateUtil;

import static hibEntities.QBuyer.*;
import static hibEntities.QGoods.*;
import static hibEntities.QGoods.goods;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class BuyerServiceTest {
    private final SessionFactory factory = HibernateUtil.buildSessionFactory();
    private final BuyerService buyerService = BuyerService.getInstance();


    @AfterAll
    void afterAll() {

        factory.close();
    }


    @Test
    void findBuyersSize() {

        var session = factory.getCurrentSession();

        session.beginTransaction();

        var goods = new JPAQuery<Goods>(session)
                .select(QGoods.goods)
                .from(QGoods.goods)
                .limit(3)
                .fetch();
        session.getTransaction().commit();

        System.out.println(goods.size());

        assertThat(goods).hasSize(3);

    }

    @Test
    void findBuyerByEmailAndPassword() {
        var maybeReadBuyerDtoHib = buyerService.login2("easton12345@gmail.com", "qwerty");
        assertThat(maybeReadBuyerDtoHib).isPresent();

    }
}
