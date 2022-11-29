package lesson1;

import java.sql.*;

public class main {
    public static void main(String[] args) throws SQLException {
        try {//flaw 1: checked exceptions
            Connection connection = DriverManager.getConnection("jdbc:h2:mem:test","sa","");
            Statement statement = connection.createStatement();
            try {
                statement.execute("CREATE TABlE Person(id INTEGER, name VARCHAR)");
                statement.execute("INSERT INTO Person VALUES (0, 'Bogdan1')");
                statement.execute("INSERT INTO Person VALUES (1, 'Bogdan2')");
                ResultSet resultSet = statement.executeQuery("SELECT * FROM Person");
                System.out.println(resultSet.isClosed());
                while (resultSet.next()){
                    System.out.println(resultSet.getString("name"));
                }
                resultSet.close();
                statement.close();
                connection.close();
            } catch (SQLException e) {
                System.out.println("Input Error");
            }
        } catch (
                SQLException e) {
            e.printStackTrace();
        }

    }
}
