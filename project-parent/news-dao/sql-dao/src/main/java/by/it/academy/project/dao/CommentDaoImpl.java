package by.it.academy.project.dao;

import by.it.academy.project.model.Comment;
import by.it.academy.project.model.User;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public class CommentDaoImpl extends AbstractDao implements CommentDao {

    private static CommentDaoImpl INSTANCE = new CommentDaoImpl();

    protected CommentDaoImpl() {
        super(LoggerFactory.getLogger(CommentDaoImpl.class));
    }

    private final static String INSERT_COMMENT =
            "INSERT INTO comment(user_id, text, date, likes, article_id) VALUE(?,?,?,?,?)";

    private final static String SELECT_COMMENT_BY_ID =
            "SELECT c.*, u.*, r.role_name FROM comment c " +
                    "JOIN user u ON c.user_id=u.id " +
                    "JOIN role r ON u.role_id = r.id WHERE c.id = ?;";

    private final static String UPDATE_COMMENT =
            "UPDATE comment SET user_id = ?, text = ?, date = ?, likes = ?, article_id = ? WHERE id = ?;";

    private final static String DELETE_COMMENT =
            "DELETE FROM comment WHERE id = ?;";

    private final static String SELECT_ALL =
            "SELECT c.*, u.*, r.role_name FROM comment c " +
                    "JOIN user u ON c.user_id = u.id " +
                    "JOIN role r ON u.role_id = r.id ORDER BY c.id DESC;";

    @Override
    public Long save(Comment comment) throws SQLException {
        ResultSet resultSet = null;
        Long result = null;
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(INSERT_COMMENT, Statement.RETURN_GENERATED_KEYS)) {

            statement.setLong(1, comment.getUser().getId());
            statement.setString(2, comment.getText());

            Timestamp timestamp = new Timestamp(comment.getDate().getTime());
            statement.setObject(3, timestamp);

            statement.setLong(4, comment.getLikes());
            statement.setLong(5, comment.getArticle_id());

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
    public Optional<Comment> read(Long id) throws SQLException {

        ResultSet resultSet = null;
        Optional<Comment> result = Optional.empty();

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_COMMENT_BY_ID)) {

            statement.setLong(1, id);

            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                result = Optional.of(mapComment(resultSet));
            }
        } finally {
            closeQuietly(resultSet);
        }
        return result;
    }

    @Override
    public int update(Comment comment) throws SQLException {
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_COMMENT)) {

            statement.setLong(1, comment.getUser().getId());
            statement.setString(2, comment.getText());

            Timestamp timestamp = new Timestamp(comment.getDate().getTime());
            statement.setObject(3, timestamp);

            statement.setLong(4, comment.getLikes());

            statement.setLong(5, comment.getArticle_id());

            statement.setLong(6, comment.getId());

            return statement.executeUpdate();
        }
    }

    @Override
    public int delete(Long id) throws SQLException {
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_COMMENT)) {

            statement.setLong(1, id);

            return statement.executeUpdate();
        }
    }

    @Override
    public List<Comment> getAll() throws SQLException {
        ResultSet resultSet = null;
        List<Comment> result = new ArrayList<>();
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_ALL)) {

            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                result.add(mapComment(resultSet));
            }

        } finally {
            closeQuietly(resultSet);
        }
        return result;
    }

    private Comment mapComment(ResultSet resultSet) throws SQLException {

        Long id = resultSet.getLong("id");

        User user = new User(resultSet.getLong("user_id"),
                resultSet.getString("username"), resultSet.getString("password"),
                resultSet.getString("salt"), resultSet.getString("role_name"));

        String text = resultSet.getString("text");

        Timestamp timestamp = (Timestamp) resultSet.getObject("date");
        Date date = new Date(timestamp.getTime());

        Long likes = resultSet.getLong("likes");

        Long article_id = resultSet.getLong("article_id");

        return new Comment(id, user, text, date, likes, article_id);
    }

    public static CommentDaoImpl getINSTANCE() {
        return INSTANCE;
    }
}
