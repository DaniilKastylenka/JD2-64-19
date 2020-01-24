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
            "INSERT INTO user(username, password, salt, role_id) VALUE (?,?,?,?)";

    private final static String SELECT_ROLE_ID = "SELECT id FROM role WHERE role_name = ?;";

    private final static String SELECT_USER_BY_ID =
            "SELECT * FROM user LEFT JOIN role r ON user.role_id = r.id WHERE user.id = ?;";

    private final static String SELECT_BY_USERNAME_AND_PASSWORD =
            "SELECT * FROM user LEFT JOIN role r on user.role_id = r.id WHERE username=? AND password = ?;";

    private final static String SELECT_BY_USERNAME =
            "SELECT * FROM user LEFT JOIN role r on user.role_id = r.id WHERE username = ?;";

    private final static String SELECT_ALL_USERS =
            "SELECT u.*, r.role_name FROM user u LEFT JOIN role r on u.role_id = r.id ORDER BY u.id";

    private final static String DELETE_USER_BY_ID =
            "DELETE FROM user WHERE id=?;";

    private final static String UPDATE_USER =
            "UPDATE user JOIN role SET username = ?, password = ?, salt = ?, role_id = ? WHERE user.id = ?";

    @Override
    public Long save(User user) throws SQLException {

        ResultSet resultSet = null;
        Long result = null;
        Long roleId = null;

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(INSERT_USER, Statement.RETURN_GENERATED_KEYS);
             PreparedStatement statementRole = connection.prepareStatement(SELECT_ROLE_ID)) {

            statementRole.setString(1, user.getRole());
            resultSet = statementRole.executeQuery();
            if (resultSet.next()) {
                roleId = resultSet.getLong(1);
            }

            if (roleId == null) {
                throw new RuntimeException("unknown user role");
            }

            statement.setString(1, user.getUsername());
            statement.setString(2, user.getPassword());
            statement.setString(3, user.getSalt());
            statement.setLong(4, roleId);
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
                result = Optional.of(mapUser(resultSet));
            }

        } finally {
            closeQuietly(resultSet);
        }

        return result;
    }

    @Override
    public int update(User user) throws SQLException {
        ResultSet resultSet = null;
        Long roleId = null;
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_USER);
             PreparedStatement roleStatement = connection.prepareStatement(SELECT_ROLE_ID)) {
            statement.setString(1, user.getUsername());
            statement.setString(2, user.getPassword());
            statement.setString(3, user.getSalt());

            roleStatement.setString(1, user.getRole());
            resultSet = roleStatement.executeQuery();
            if (resultSet.next()){
                roleId = resultSet.getLong(1);
            } else {
                throw new RuntimeException("unknown user role");
            }

            statement.setLong(4, roleId);
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
                result.add(mapUser(resultSet));
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
                result = Optional.of(mapUser(resultSet));
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
                result = Optional.of(mapUser(resultSet));
            }

        } finally {
            closeQuietly(resultSet);
        }

        return result;
    }

    private User mapUser(ResultSet resultSet) throws SQLException {

        Long userId = resultSet.getLong("id");

        String username = resultSet.getString("username");
        String password = resultSet.getString("password");

        String role = resultSet.getString("role_name");
        String salt = resultSet.getString("salt");

        return new User(userId, username, password, salt, role);
    }
}