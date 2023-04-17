package util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;

import java.sql.Connection;
import java.sql.DriverManager;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ConnectionManager {



    public static final String PASSWORD_KEY = "db.password";
    public static final String USER_KEY = "db.user";
    public static final String URL_KEY = "db.url";

    static {
        loadDriver();
    }
    @SneakyThrows
    private static void loadDriver() {

        Class.forName("org.postgresql.Driver");

    }

    @SneakyThrows
    public static Connection open() {


        return DriverManager.getConnection(
                PropertiesUtil.get(URL_KEY),
                PropertiesUtil.get(USER_KEY),
                PropertiesUtil.get(PASSWORD_KEY)
        );

    }


}
