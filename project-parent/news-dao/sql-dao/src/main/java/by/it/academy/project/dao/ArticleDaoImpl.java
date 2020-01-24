package by.it.academy.project.dao;

import by.it.academy.project.model.Article;
import by.it.academy.project.model.Section;
import by.it.academy.project.model.User;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ArticleDaoImpl extends AbstractDao implements ArticleDao {

    private ArticleDaoImpl() {
        super(LoggerFactory.getLogger(ArticleDaoImpl.class));
    }

    private static ArticleDaoImpl INSTANCE = new ArticleDaoImpl();

    private static final String INSERT_ARTICLE =
            "INSERT INTO article (title, section_id, author_id, date, text) VALUES (?,?,?,?,?);";

    private static final String SELECT_ARTICLE_BY_ID =
            "SELECT a.*, s.section_name, u.*, r.role_name FROM article a " +
                    "JOIN section s ON a.section_id = s.id " +
                    "JOIN user u ON a.author_id = u.id " +
                    "JOIN role r ON u.role_id = r.id WHERE a.id = ?";

    private static final String UPDATE_ARTICLE =
            "UPDATE article SET title = ?, section_id = ?, author_id = ?, text = ? WHERE id=?;";

    private static final String DELETE_ARTICLE =
            "DELETE FROM article WHERE id = ?;";

    private static final String SELECT_ALL_ARTICLES =
            "SELECT a.*, s.section_name, u.*, r.role_name FROM article a " +
                    "JOIN section s ON a.section_id = s.id " +
                    "JOIN user u ON a.author_id = u.id " +
                    "JOIN role r ON u.role_id = r.id ORDER BY a.id DESC;";


    private static final String INSERT_LIKE =
            "INSERT INTO like_on_article (article_id, user_id)VALUE (?,?);";

    private static final String DELETE_LIKE =
            "DELETE FROM like_on_article WHERE article_id = ? AND user_id = ?;";

    private static final String FIND_LIKE =
            "SELECT * FROM like_on_article WHERE article_id = ? AND user_id = ?;";

    private static final String UPDATE_LIKE =
            "UPDATE article SET likes = ? WHERE id = ?;";


    public static ArticleDao getINSTANCE() {
        return INSTANCE;
    }


    @Override
    public Long save(Article article) throws SQLException {

        ResultSet resultSet = null;
        Long result = null;

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(INSERT_ARTICLE)) {

            statement.setString(1, article.getTitle());
            statement.setLong(2, article.getSection_id());
            statement.setLong(3, article.getAuthor_id());
            statement.setObject(4, new Timestamp(article.getDate().getTime()));
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
            statement.setLong(2, article.getSection_id());
            statement.setLong(3, article.getAuthor_id());
            statement.setString(4, article.getText());
            statement.setLong(5, article.getId());

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
                resultSet.getString("salt"), resultSet.getString("role_name"));

        Timestamp timestamp = (Timestamp) resultSet.getObject("date");
        Date date = new Date(timestamp.getTime());

        String text = resultSet.getString("text");

        Long likes = resultSet.getLong("likes");

        return new Article(article_id, title, section, author, date, text, likes);
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
                statement.setLong(1, article.getLikes() + 1);
            } else {
                statement.setLong(1, article.getLikes() - 1);
            }
            statement.setLong(2, article_id);
            return statement.executeUpdate();
        }
    }


}
