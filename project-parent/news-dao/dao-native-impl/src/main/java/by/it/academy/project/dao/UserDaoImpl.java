package by.it.academy.project.dao;

import by.it.academy.project.model.User;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserDaoImpl extends AbstractDao implements UserDao {

    private UserDaoImpl() {
        super(LoggerFactory.getLogger(UserDaoImpl.class));
    }

    private static UserDaoImpl INSTANCE = new UserDaoImpl();


    public static UserDaoImpl getINSTANCE() {
        return INSTANCE;
    }

    private final static String INSERT_USER =
            "INSERT INTO user(U_username, U_password, U_salt, U_role_id) VALUE (?,?,?,?)";

    private final static String SELECT_USER_BY_ID =
            "SELECT * FROM user LEFT JOIN role r ON user.U_role_id = r.R_id WHERE user.U_id = ?;";

    private final static String SELECT_BY_USERNAME_AND_PASSWORD =
            "SELECT * FROM user LEFT JOIN role r on user.U_role_id = r.R_id WHERE U_username=? AND U_password = ?;";

    private final static String SELECT_BY_USERNAME =
            "SELECT * FROM user LEFT JOIN role r on user.U_role_id = r.R_id WHERE U_username = ?;";

    private final static String SELECT_ALL_USERS =
            "SELECT * FROM user u LEFT JOIN role r on u.U_role_id = r.R_id ORDER BY u.U_id";

    private final static String DELETE_USER_BY_ID =
            "DELETE FROM user WHERE U_id=?;";

    private final static String UPDATE_USER =
            "UPDATE user SET U_username = ?, U_password = ?, U_salt = ?, U_role_id = ? WHERE user.U_id = ?";

    @Override
    public Long create(User user) throws SQLException {

        ResultSet resultSet = null;
        Long result = null;

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(INSERT_USER, Statement.RETURN_GENERATED_KEYS)) {

            statement.setString(1, user.getUsername());
            statement.setString(2, user.getPassword());
            statement.setString(3, user.getSalt());
            statement.setInt(4, user.getRole().getId());
            statement.executeUpdate();

            resultSet = statement.getGeneratedKeys();

            while (resultSet.next()) {
                result = resultSet.getLong(1);
            }

        } finally {
            closeQuietly(resultSet);
        }

        return result;
    }

    @Override
    public Optional<User> read(Long id) throws SQLException {

        ResultSet resultSet = null;
        Optional<User> result = Optional.empty();

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_USER_BY_ID)) {

            statement.setLong(1, id);
            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                result = Optional.of(Mapper.mapUser(resultSet));
            }

        } finally {
            closeQuietly(resultSet);
        }

        return result;
    }

    @Override
    public int update(User user) throws SQLException {
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_USER)) {
            statement.setString(1, user.getUsername());
            statement.setString(2, user.getPassword());
            statement.setString(3, user.getSalt());
            statement.setLong(4, user.getRole().getId());
            statement.setLong(5, user.getId());


            return statement.executeUpdate();
        }
    }

    @Override
    public int delete(Long id) throws SQLException {
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_USER_BY_ID)) {
            statement.setLong(1, id);

            return statement.executeUpdate();
        }
    }

    @Override
    public List<User> getAll() throws SQLException {
        ResultSet resultSet = null;
        List<User> result = new ArrayList<>();
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_ALL_USERS)) {
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                result.add(Mapper.mapUser(resultSet));
            }
        } finally {
            closeQuietly(resultSet);
        }
        return result;
    }

    @Override
    public Optional<User> findUserByUsernameAndPassword(String username, String password) throws SQLException {

        ResultSet resultSet = null;
        Optional<User> result = Optional.empty();

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_BY_USERNAME_AND_PASSWORD)) {

            statement.setString(1, username);
            statement.setString(2, password);

            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                result = Optional.of(Mapper.mapUser(resultSet));
            }

        } finally {
            closeQuietly(resultSet);
        }

        return result;
    }

    @Override
    public Optional<User> findUserByUsername(String username) throws SQLException {

        ResultSet resultSet = null;
        Optional<User> result = Optional.empty();

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_BY_USERNAME)) {

            statement.setString(1, username);

            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                result = Optional.of(Mapper.mapUser(resultSet));
            }

        } finally {
            closeQuietly(resultSet);
        }

        return result;
    }

}
