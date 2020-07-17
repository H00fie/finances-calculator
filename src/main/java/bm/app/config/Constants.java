package bm.app.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Constants {

    public static final String DEFAULT_DRIVER = "com.mysql.cj.jdbc.Driver";
    public static final String DEFAULT_URL = "jdbc:mysql://localhost:3306/financialproducts?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    public static final String DEFAULT_USERNAME = "root";
    public static final String DEFAULT_PASSWORD = "password";

    public static Connection getConnection(String driver, String url, String username, String password){
        try {
            Class.forName(driver);
            return DriverManager.getConnection(url, username, password);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
