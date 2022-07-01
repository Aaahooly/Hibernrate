package jm.task.core.jdbc.util;
import com.mysql.cj.xdevapi.PreparableStatement;

import java.sql.*;

public class Util {

    static  private final String dbUrl = "jdbc:mysql://localhost:3306/testBase";
    static private final String dbName = "root";
    static private final String dbPassword = "Aaahooly2005199518796.";

    private Connection connection;

    public Connection getConnection() {
        return connection;
    }

    public Util(){
        try {
            connection = DriverManager.getConnection(dbUrl,dbName,dbPassword);
        } catch (SQLException e) {
            System.out.println("Соединение не получено");
            e.printStackTrace();
        }
    }
//    public static Connection connectionDB(){
//         try(Connection connection = DriverManager.getConnection(dbUrl,dbName,dbPassword)) {
//         return connection;
//         } catch (SQLException e) {
//             throw new RuntimeException(e);
//         }
//     }
}
