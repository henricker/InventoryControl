package database;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class ConnectionFactory {
    private static final String URL = "jdbc:postgresql://batyr.db.elephantsql.com/prhpdsxb";
    private static final String USERNAME = "prhpdsxb";
    private static final String PASSWORD = "zrNl84odG7bWKI9iTr9puB1QxJZ7lQBL";

    public static DataSource dataSource;

   public static Connection getConnection()  {
       try {
           return DriverManager.getConnection(URL, USERNAME, PASSWORD);
       } catch (SQLException throwables) {
           throwables.printStackTrace();
       }
       return null;
   }

}
