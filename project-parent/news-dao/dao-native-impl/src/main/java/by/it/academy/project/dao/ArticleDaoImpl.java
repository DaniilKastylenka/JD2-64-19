package by.it.academy.project.dao;

import by.it.academy.project.model.Article;
import by.it.academy.project.model.Role;
import by.it.academy.project.model.Section;
import by.it.academy.project.model.User;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.sql.Date;
import java.util.*;

public class ArticleDaoImpl extends AbstractDao implements ArticleDao {

    private ArticleDaoImpl() {
        super(LoggerFactory.getLogger(ArticleDaoImpl.class));
    }

    private static ArticleDaoImpl INSTANCE = new ArticleDaoImpl();

    private static final String INSERT_ARTICLE =
            "INSERT INTO article (title, section_id, author_id, publication_date, text) VALUES (?,?,?,?,?);";

    private static final String SELECT_ARTICLE_BY_ID =
            "SELECT a.*, s.section_name, u.*, r.role_name   FROM article a " +
                    "JOIN section s ON a.section_id = s.id " +
                    "JOIN user u ON a.author_id = u.id " +
                    "JOIN role r ON u.role_id = r.id WHERE a.id = ?";

    private static final String UPDATE_ARTICLE =
            "UPDATE article SET title = ?, section_id = ?, author_id = ?, text = ?, updated_date = ? WHERE id=?;";

    private static final String DELETE_ARTICLE =
            "DELETE FROM article WHERE id = ?;";

    private static final String SELECT_ALL_ARTICLES =
            "SELECT a.*, s.section_name, u.*, r.role_name FROM article a " +
                    "JOIN section s ON a.section_id = s.id " +
                    "JOIN user u ON a.author_id = u.id " +
                    "JOIN role r ON u.role_id = r.id ORDER BY a.id DESC;";


    private static final String SELECT_ALL_WHO_LIKED =
            "SELECT l.*, u.*, r.* FROM like_on_article l " +
                    "JOIN user u ON l.user_id = u.id " +
                    "JOIN role r ON u.role_id = r.id WHERE article_id = ?;";


    private static final String INSERT_LIKE =
            "INSERT INTO like_on_article (article_id, user_id)VALUE (?,?);";

    private static final String DELETE_LIKE =
            "DELETE FROM like_on_article WHERE article_id = ? AND user_id = ?;";

    private static final String FIND_LIKE =
            "SELECT * FROM like_on_article WHERE article_id = ? AND user_id = ?;";

    private static final String UPDATE_LIKE =
            "UPDATE article SET number_of_likes = ? WHERE id = ?;";


    public static ArticleDao getINSTANCE() {
        return INSTANCE;
    }


    @Override
    public Long create(Article article) throws SQLException {

        ResultSet resultSet = null;
        Long result = null;

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(INSERT_ARTICLE, Statement.RETURN_GENERATED_KEYS)) {

            statement.setString(1, article.getTitle());
            statement.setLong(2, article.getSection().getId());
            statement.setLong(3, article.getAuthor().getId());
            statement.setObject(4, new Timestamp(article.getPublicationDate().getTime()));
            statement.setString(5, article.getText());
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
    public Optional<Article> read(Long id) throws SQLException {

        ResultSet resultSet = null;

        Optional<Article> result = Optional.empty();

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_ARTICLE_BY_ID)) {

            statement.setLong(1, id);
            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                result = Optional.of(mapArticle(resultSet));
            }

        } finally {
            closeQuietly(resultSet);
        }

        return result;
    }

    @Override
    public int update(Article article) throws SQLException {

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_ARTICLE)) {

            statement.setString(1, article.getTitle());
            statement.setLong(2, article.getSection().getId());
            statement.setLong(3, article.getAuthor().getId());
            statement.setString(4, article.getText());
            java.util.Date date = new java.util.Date();
            statement.setObject(5, new Timestamp(date.getTime()));
            statement.setLong(6, article.getId());

            return statement.executeUpdate();

        }
    }

    @Override
    public int delete(Long id) throws SQLException {
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_ARTICLE)) {
            statement.setLong(1, id);
            return statement.executeUpdate();
        }
    }

    @Override
    public List<Article> getAll() throws SQLException {

        ResultSet resultSet = null;
        List<Article> articles = new ArrayList<>();

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_ALL_ARTICLES)) {

            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                articles.add(mapArticle(resultSet));
            }

        } finally {
            closeQuietly(resultSet);
        }

        return articles;
    }

    private Article mapArticle(ResultSet resultSet) throws SQLException {

        Long article_id = resultSet.getLong("id");

        String title = resultSet.getString("title");

        Section section = new Section(resultSet.getLong("section_id"), resultSet.getString("section_name"));

        User author = new User(resultSet.getLong("author_id"),
                resultSet.getString("username"), resultSet.getString("password"),
                resultSet.getString("salt"), new Role(resultSet.getInt("role_id"), resultSet.getString("role_name")));

        Timestamp timestamp = resultSet.getTimestamp("publication_date");

        Date publicationDate = new Date(timestamp.getTime());

        Timestamp timestamp1 = resultSet.getTimestamp("updated_date");

        java.util.Date updatedDate = null;

        if (timestamp1 != null) {
            updatedDate = new Date(timestamp1.getTime());
        }
        String text = resultSet.getString("text");

        Long numberOfLikes = resultSet.getLong("number_of_likes");

        Set<User> usersWhoLiked = new HashSet<>(getUsersWhoLiked(article_id));

        return new Article(article_id, section, title, text, author, publicationDate, updatedDate, numberOfLikes, usersWhoLiked);
    }

    @Override
    public Set<User> getUsersWhoLiked(Long articleId) throws SQLException {
        ResultSet resultSet = null;
        Set<User> result = new HashSet<>();
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_ALL_WHO_LIKED)) {
            statement.setLong(1, articleId);
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
    public User mapUser(ResultSet resultSet) throws SQLException {
        Long userId = resultSet.getLong("user_id");
        String username = resultSet.getString("username");
        String password = resultSet.getString("password");
        String salt = resultSet.getString("salt");
        Role role = new Role(resultSet.getInt("role_id"), resultSet.getString("role_name"));
        return new User(userId, username, password, salt, role);
    }

    @Override
    public Long addLike(Long article_id, Long user_id) throws SQLException {
        ResultSet resultSet = null;
        Long result = null;
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(INSERT_LIKE, Statement.RETURN_GENERATED_KEYS)) {

            statement.setLong(1, article_id);
            statement.setLong(2, user_id);

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
    public int deleteLike(Long article_id, Long user_id) throws SQLException {
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_LIKE)) {

            statement.setLong(1, article_id);
            statement.setLong(2, user_id);

            return statement.executeUpdate();
        }
    }

    @Override
    public boolean findLike(Long article_id, Long user_id) throws SQLException {
        ResultSet resultSet = null;
        boolean result = false;
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_LIKE)) {

            statement.setLong(1, article_id);
            statement.setLong(2, user_id);

            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                result = true;
            }
        } finally {
            closeQuietly(resultSet);
        }
        return result;
    }

    @Override
    public int updateLikeInArticle(Long article_id, boolean like) throws SQLException {
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_LIKE)) {

            Article article = read(article_id)
                    .orElseThrow(() -> new RuntimeException("unknown article"));

            if (like) {
                statement.setLong(1, article.getNumberOfLikes() + 1);
            } else {
                statement.setLong(1, article.getNumberOfLikes() - 1);
            }
            statement.setLong(2, article_id);
            return statement.executeUpdate();
        }
    }


}
