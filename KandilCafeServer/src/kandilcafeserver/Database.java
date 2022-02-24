package kandilcafeserver;
import java.sql.*;

public class Database {
    public static Connection con () throws SQLException, ClassNotFoundException {
    Class.forName("com.mysql.jdbc.Driver");
    System.out.println("Driver Successfully loaded");
    Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/kandilcafe","root", "");
    System.out.println("Database Successfully Connected");
    return  connection;
    }
}
