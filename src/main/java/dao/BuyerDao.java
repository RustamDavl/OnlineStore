package dao;

import entities.Buyer;
import entities.Orders;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;
import util.ConnectionManager;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class BuyerDao implements Dao<Integer, Buyer> {

    private static final BuyerDao INSTANCE = new BuyerDao();
    private final String SHOW_BY_EMAIL_PASSWORD_SQL = """
            
            SELECT *
            FROM buyer
            WHERE email = ? AND password = ?;
            
            """;



    private final String SHOW_ALL = """
            
            SELECT *
            FROM buyer
            """;



    private  final String SAVE_SQL = """


        INSERT INTO buyer(name, last_name, email, password, phone, address, birthday) 
        VALUES (?, ?, ?, ?, ?, ?, ?);
""";




    @Override
    @SneakyThrows
    public List<Buyer> findAll() {

        try (var connection = ConnectionManager.open();
             PreparedStatement preparedStatement = connection.prepareStatement(SHOW_ALL);
        ){

            var result = preparedStatement.executeQuery();

            List<Buyer> buyers = new ArrayList<>();

            while (result.next()) {
                buyers.add(Buyer.builder()
                        .id(result.getInt("id"))
                        .name(result.getString("name"))
                        .lastName(result.getString("last_name"))
                        .email(result.getString("email"))
                        .password(result.getString("password"))
                        .phone(result.getString("phone"))
                        .address(result.getString("address"))
                        .birthday(result.getDate("birthday").toLocalDate())
                        .build());
            }


            return buyers;

        }



    }

    @Override
    public Optional<Buyer> findById(Integer id) {
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
    @Override
    public Buyer save(Buyer entity) {

        try(var connection = ConnectionManager.open();
        var preparedStatement = connection.prepareStatement(SAVE_SQL, Statement.RETURN_GENERATED_KEYS)
        ) {

            preparedStatement.setString(1, entity.getName());
            preparedStatement.setString(2, entity.getLastName());
            preparedStatement.setString(3, entity.getEmail());
            preparedStatement.setString(4, entity.getPassword());
            preparedStatement.setString(5, entity.getPhone());
            preparedStatement.setString(6, entity.getAddress());
            preparedStatement.setDate(7, Date.valueOf(entity.getBirthday()));

            preparedStatement.executeUpdate();

            var key = preparedStatement.getGeneratedKeys();

            if(key.next())
                entity.setId(key.getInt("id"));

            return entity;




        }
    }
    @SneakyThrows
    public Optional<Buyer> getByEmailAndPassword(String email, String password) {

        try(var connection = ConnectionManager.open();
            PreparedStatement preparedStatement = connection.prepareStatement(SHOW_BY_EMAIL_PASSWORD_SQL)
        ) {
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, password);

            var resultSet = preparedStatement.executeQuery();

            Buyer buyer = null;
            if(resultSet.next()) {
                buyer = Buyer.builder()
                        .id(resultSet.getInt("id"))
                        .name(resultSet.getString("name"))
                        .lastName(resultSet.getString("last_name"))
                        .email(resultSet.getString("email"))
                        .password(resultSet.getString("password"))
                        .phone(resultSet.getString("phone"))
                        .address(resultSet.getString("address"))
                        .birthday(resultSet.getDate("birthday").toLocalDate())
                        .build();
            }

            return Optional.ofNullable(buyer);

        }
    }

    public static BuyerDao getInstance() {
        return INSTANCE;
    }
}
