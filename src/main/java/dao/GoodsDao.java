package dao;

import dto.GoodsFilter;
import entities.Buyer;
import entities.Goods;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;
import util.ConnectionManager;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class GoodsDao implements Dao<Integer, Goods> {

    private static final GoodsDao INSTANCE = new GoodsDao();


    private final String GET_QUANTITY_SQL = """
           
           SELECT remaining_quantity
           from goods
           where product_name = ?
            """;


    private final String UPDATE_AMOUNT_SQL = """
            
            
            UPDATE goods
            SET remaining_quantity = ?
            WHERE product_name = ?;
                """;


    private final String GET_DESCRIPTION_BY_NAME_SQL = """
            
            SELECT description
            FROM goods
            WHERE product_name = ?
        
            
            """;
    private final String COUNT_ROWS_SQL = """
            
            SELECT count(product_name) as amount
            FROM goods
            """;

    private final String SHOW_ALL_SQL = """
            SELECT *
            FROM goods
             
             """;

    private final String DELETE_BY_NAME_SQL = """
                        
            DELETE FROM goods
            WHERE product_name = ?
            """;
    private final String SAVE_SQL = """
                        
            INSERT INTO goods(product_name, description, price, remaining_quantity,image) 
            VALUES (?, ?, ?, ?, ?)
            """;

    @SneakyThrows
    @Override
    public List<Goods> findAll() {
        try (var connection = ConnectionManager.open();
             PreparedStatement preparedStatement = connection.prepareStatement(SHOW_ALL_SQL)
        ) {


            var resultSet = preparedStatement.executeQuery();

            List<Goods> goodsList = new ArrayList<>();
            while (resultSet.next()) {
                goodsList.add(createGoods(resultSet));
            }

            return goodsList;
        }
    }




    @SneakyThrows
    public String getDescriptionByName(String productName) {
        try (var connection = ConnectionManager.open();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_DESCRIPTION_BY_NAME_SQL)
        ){
            preparedStatement.setString(1, productName);

            var resultSet = preparedStatement.executeQuery();



               return resultSet.next() ?  (resultSet.getString("description"))
                       : "there is no description";



        }
    }

    @SneakyThrows
    public void updateAmount(int amount, String prodName) {
        try (var connection = ConnectionManager.open();
        PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_AMOUNT_SQL)
        ){
            preparedStatement.setInt(1,  + getQuantity(prodName) + amount);
            preparedStatement.setString(2, prodName);
            preparedStatement.executeUpdate();

        }


    }
    @SneakyThrows
    public void decreaseAmount(int amount, String prodName) {
        try (var connection = ConnectionManager.open();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_AMOUNT_SQL)
        ){
            preparedStatement.setInt(1,  + getQuantity(prodName) - amount);
            preparedStatement.setString(2, prodName);
            preparedStatement.executeUpdate();

        }


    }

    @SneakyThrows
    public int getQuantity(String prodName) {
        try (var connection = ConnectionManager.open();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_QUANTITY_SQL)
        ){
            preparedStatement.setString(1, prodName);
            var result = preparedStatement.executeQuery();

            result.next();

            return result.getInt("remaining_quantity");

        }
    }


    @SneakyThrows
    public List<Goods> findAll(GoodsFilter filter) {
        var WITH_CONDITION = SHOW_ALL_SQL + """
                LIMIT ?
                            
                               
                 """;
        try (var connection = ConnectionManager.open();
             PreparedStatement preparedStatement = connection.prepareStatement(WITH_CONDITION)
        ) {
            preparedStatement.setInt(1, filter.limit());
            //preparedStatement.setInt(2, filter.offset());


            var resultSet = preparedStatement.executeQuery();

            List<Goods> goodsList = new ArrayList<>();
            while (resultSet.next()) {
                goodsList.add(createGoods(resultSet));
            }

            return goodsList;
        }
    }

    @SneakyThrows
    private Goods createGoods(ResultSet resultSet) {
        return Goods.builder()
                .productName(resultSet.getString("product_name"))
                .description(resultSet.getString("description"))
                .price(resultSet.getBigDecimal("price"))
                .remainingQuantity(resultSet.getInt("remaining_quantity"))
                .pathToImage(resultSet.getString("image"))
                .build();
    }

    @Override
    public Optional<Goods> findById(Integer id) {
        return Optional.empty();
    }

    @Override
    public boolean delete(Integer id) {
        return false;
    }

    @Override
    public void update(Integer entity) {

    }

    @SneakyThrows
    public Integer deleteByName(String productName) {
        try (var connection = ConnectionManager.open();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_BY_NAME_SQL, Statement.RETURN_GENERATED_KEYS);
        ) {
            preparedStatement.setString(1, productName);


            return preparedStatement.executeUpdate();


        }
    }


    @SneakyThrows
    public Integer getAmountOfRows() {
        try(var connection = ConnectionManager.open();
        PreparedStatement preparedStatement = connection.prepareStatement(COUNT_ROWS_SQL)
        ) {
            var resulSet = preparedStatement.executeQuery();
            int amount = 0;
            if(resulSet.next())
               amount = resulSet.getInt("amount");

            return amount;
        }
    }


    @SneakyThrows
    @Override
    public Goods save(Goods entity) {

        try (var connection = ConnectionManager.open();
             PreparedStatement preparedStatement = connection.prepareStatement(SAVE_SQL, Statement.RETURN_GENERATED_KEYS)
        ) {


            preparedStatement.setString(1, entity.getProductName());
            preparedStatement.setString(2, entity.getDescription());
            preparedStatement.setBigDecimal(3, entity.getPrice());
            preparedStatement.setInt(4, entity.getRemainingQuantity());
            preparedStatement.setString(5, entity.getPathToImage());
            preparedStatement.executeUpdate();

            var keySet = preparedStatement.getGeneratedKeys();

            keySet.next();

            int id = keySet.getInt("id");
            entity.setId(id);

            return entity;


        }
    }


    public static GoodsDao getInstance() {
        return INSTANCE;
    }
}
