package dao;

import entities.Orders;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;
import util.ConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class OrdersDao implements Dao<Integer, Orders> {

    private static final OrdersDao INSTANCE = new OrdersDao();
    private final String GET_GOODS_SQL = """
            SELECT number,goods
            FROM orders
            WHERE buyer_id = ?
            """;

    private final String CREATE_ORDER_SQL = """
                        INSERT INTO orders(buyer_id, goods) 
                        VALUES (?, ?)
            """;


    public static OrdersDao getInstance() {
        return INSTANCE;
    }


    @SneakyThrows
    public List<Orders> findAllById(Integer id) {
        try (var connection = ConnectionManager.open();
        PreparedStatement preparedStatement = connection.prepareStatement(GET_GOODS_SQL)
        ){
            preparedStatement.setInt(1,id);
            var result = preparedStatement.executeQuery();

            List<Orders> ordersList = new ArrayList<>();

            while (result.next()) {
                ordersList.add(Orders.builder()
                        .number(result.getInt("number"))
                        .prodName(result.getString("goods"))
                        .build());
            }

            return ordersList;

        }
    }
    @Override
    public List<Orders> findAll() {
        return null;
    }

    @Override
    public Optional<Orders> findById(Integer id) {
        return Optional.empty();
    }

    @Override
    public boolean delete(Integer id) {
        return false;
    }

    @Override
    public void update(Integer entity) {

    }

    @Override
    @SneakyThrows
    public Orders save(Orders entity) {

        GoodsDao goodsDao = GoodsDao.getInstance();
        final String UPDATE_AMOUNT_SQL = """
                            
                            
                UPDATE goods
                SET remaining_quantity = ?
                WHERE product_name = ?;
                    """;



        Connection connection = null;
        PreparedStatement updateAmount = null;
        PreparedStatement saveOrder = null;


        try {
            connection = ConnectionManager.open();
            updateAmount = connection.prepareStatement(UPDATE_AMOUNT_SQL);
            saveOrder = connection.prepareStatement(CREATE_ORDER_SQL, Statement.RETURN_GENERATED_KEYS);
            connection.setAutoCommit(false);

            updateAmount.setInt(1, goodsDao.getQuantity(entity.getProdName()) - entity.getAmount());
            updateAmount.setString(2, entity.getProdName());
            saveOrder.setInt(1, entity.getBuyerId());
            saveOrder.setString(2,entity.getProdName() + ";" + entity.getAmount());

            updateAmount.executeUpdate();
            saveOrder.executeUpdate();

            var result = saveOrder.getGeneratedKeys();

            if (result.next())
                entity.setNumber(result.getInt("number"));


            connection.commit();

        } catch (Exception e) {
            if(connection != null) {
                connection.rollback();
            }
            throw e;
        }
        finally {
            if(connection != null) {
                connection.close();
            }
            if(updateAmount != null) {
                updateAmount.close();
            }
            if(saveOrder != null) {
                saveOrder.close();
            }
        }
        return entity;
    }
}
