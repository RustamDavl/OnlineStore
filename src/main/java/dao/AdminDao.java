package dao;

import entities.Admin;
import lombok.SneakyThrows;
import util.ConnectionManager;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;
import java.util.Optional;

public class AdminDao implements Dao<Integer, Admin> {

    private final String FIND_BY_PASSWORD_EMAIL = """
                        
            SELECT *
            FROM admins
            WHERE email = ? AND password = ?
            """;
    private final String SAVE_SQL = """
            INSERT INTO admins(name, last_name, email, password, phone, address, birthday) 
            VALUES (?, ?, ?, ?, ?, ?, ?)
            """;
    private static final AdminDao INSTANCE = new AdminDao();

    public static AdminDao getInstance() {
        return INSTANCE;
    }

    @SneakyThrows
    public Optional<Admin> findByEmailAndPassword(String email, String password) {

        try (var connection = ConnectionManager.open();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_PASSWORD_EMAIL)
        ) {

            preparedStatement.setString(1, email);
            preparedStatement.setString(2, password);
            var result = preparedStatement.executeQuery();

            Admin admin = null;
            if (result.next()) {
                admin = Admin.builder()
                        .id(result.getInt("id"))
                        .name(result.getString("name"))
                        .lastName(result.getString("last_name"))
                        .email(result.getString("email"))
                        .password(result.getString("password"))
                        .phone(result.getString("phone"))
                        .address(result.getString("address"))
                        .birthday(result.getDate("birthday").toLocalDate())
                        .build();
            }

            return Optional.ofNullable(admin);

        }

    }

    @Override
    public List<Admin> findAll() {
        return null;
    }

    @Override
    public Optional<Admin> findById(Integer id) {
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
    public Admin save(Admin entity) {
        try (var connection = ConnectionManager.open();
             PreparedStatement preparedStatement = connection.prepareStatement(SAVE_SQL, Statement.RETURN_GENERATED_KEYS)
        ) {
            preparedStatement.setString(1, entity.getName());
            preparedStatement.setString(2, entity.getLastName());
            preparedStatement.setString(3, entity.getEmail());
            preparedStatement.setString(4, entity.getPassword());
            preparedStatement.setString(5, entity.getPhone());
            preparedStatement.setString(6, entity.getAddress());
            preparedStatement.setDate(7, Date.valueOf(entity.getBirthday()));

            preparedStatement.executeUpdate();

            var result = preparedStatement.getGeneratedKeys();

            if(result.next())
                entity.setId(result.getInt("id"));

            entity.setId(result.getInt("id"));
            return entity;


        }
    }
}
