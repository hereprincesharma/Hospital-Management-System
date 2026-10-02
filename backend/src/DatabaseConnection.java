import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    public static void main(String[] args) {

        String url = "jdbc:mysql://localhost:3306/hospital_db";
        String username = "root";
        String password = "@Prince144";

        try {
            Connection connection = DriverManager.getConnection(
                    url,
                    username,
                    password
            );

            System.out.println("Database connected successfully!");

            connection.close();

        } catch (SQLException e) {
            System.out.println("Database connection failed!");
            e.printStackTrace();
        }
    }
}
