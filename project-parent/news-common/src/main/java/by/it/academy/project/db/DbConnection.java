package by.it.academy.project.db;

import java.sql.*;

public class DbConnection {

    public Connection getConnection() throws ClassNotFoundException, SQLException {

        Class.forName("com.mysql.jdbc.Driver");

        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/test?useUnicode=true&characterEncoding=UTF-8&serverTimezone=UTC", "roman", "pass");

        return connection;

    }

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        insert();
    }


    public static void insert() throws SQLException, ClassNotFoundException {

        Connection connection = null;

        Statement statement = null;
        ResultSet resultSet = null;

        try {
            connection = new DbConnection().getConnection();
            statement = connection.createStatement();
            int i = statement.executeUpdate("insert into empl (name, dep_ip) values ('AAAA', 1)", Statement.RETURN_GENERATED_KEYS);
            System.out.println("Affected rows = " + i);

            resultSet = statement.getGeneratedKeys();

            while (resultSet.next()) {
                int id = resultSet.getInt(1);
                System.out.println("id = " + id);
            }
        } finally {
            if (resultSet != null) {
                resultSet.close();
            }
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }

    }

}
