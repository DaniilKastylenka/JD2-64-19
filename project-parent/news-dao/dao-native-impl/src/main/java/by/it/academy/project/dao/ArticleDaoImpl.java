package by.it.academy.project.dao;

import by.it.academy.project.model.Article;
import by.it.academy.project.model.Comment;
import by.it.academy.project.model.User;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.*;

public class ArticleDaoImpl extends AbstractDao implements ArticleDao {

    private ArticleDaoImpl() {
        super(LoggerFactory.getLogger(ArticleDaoImpl.class));
    }

    private static final ArticleDaoImpl INSTANCE = new ArticleDaoImpl();

    private static final String INSERT_ARTICLE =
            "INSERT INTO article (A_title, A_section_id, A_author_id, A_publication_date, A_text) VALUES (?,?,?,?,?);";

    private static final String SELECT_ARTICLE_BY_ID =
            "SELECT * FROM article a " +
                    "JOIN section s ON a.A_section_id = s.S_id " +
                    "JOIN user u ON a.A_author_id = u.U_id " +
                    "JOIN role r ON u.U_role_id = r.R_id WHERE a.A_id = ?";

    private static final String UPDATE_ARTICLE =
            "UPDATE article SET A_title = ?, A_section_id = ?, A_author_id = ?, A_text = ?, A_updated_date = ? WHERE A_id=?;";

    private static final String DELETE_ARTICLE =
            "DELETE FROM article WHERE A_id = ?;";

    private static final String SELECT_ALL_ARTICLES =
            "SELECT * FROM article a " +
                    "JOIN section s ON a.A_section_id = s.S_id " +
                    "JOIN user u ON a.A_author_id = u.U_id " +
                    "JOIN role r ON u.U_role_id = r.R_id ORDER BY a.A_publication_date DESC;";


    private static final String SELECT_ALL_WHO_LIKED =
            "SELECT * FROM user_article_like ua " +
                    "JOIN user u ON ua.User_U_id = u.U_id " +
                    "JOIN role r ON u.U_role_id = r.R_id WHERE ua.Article_A_id = ?;";


    private static final String SELECT_ALL_COMMENTS =
            "SELECT * FROM comment c " +
                    "JOIN user u ON c.C_user_id = u.U_id " +
                    "JOIN role r ON u.U_role_id = r.R_id " +
                    "JOIN article a ON c.C_article_id = a.A_id " +
                    "JOIN section s on a.A_section_id = s.S_id WHERE c.C_article_id = ?;";


    private static final String INSERT_LIKE =
            "INSERT INTO user_article_like VALUE (?,?);";

    private static final String DELETE_LIKE =
            "DELETE FROM user_article_like WHERE Article_A_id = ? AND User_U_id = ?;";

    private static final String SELECT_LIKE =
            "SELECT * FROM user_article_like WHERE Article_A_id = ? AND User_U_id = ?;";

    private static final String UPDATE_LIKE =
            "UPDATE article SET A_likes = ? WHERE A_id = ?;";

    private static final String INSERT_DISLIKE =
            "INSERT INTO user_article_dislike VALUE (?,?);";

    private static final String DELETE_DISLIKE =
            "DELETE FROM user_article_dislike WHERE Article_A_id = ? AND User_U_id = ?;";

    private static final String SELECT_DISLIKE =
            "SELECT * FROM user_article_dislike WHERE Article_A_id = ? AND User_U_id = ?;";

    private static final String UPDATE_DISLIKE =
            "UPDATE article SET A_dislikes = ? WHERE A_id = ?;";


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

            if (resultSet.next()) {
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
                result = Optional.of(Mapper.mapArticle(resultSet));
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
            statement.setInt(2, article.getSection().getId());
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
                articles.add(Mapper.mapArticle(resultSet));
            }

        } finally {
            closeQuietly(resultSet);
        }

        return articles;
    }

    private Set<User> getUsersWhoLiked(Long articleId) throws SQLException {
        ResultSet resultSet = null;
        Set<User> result = new HashSet<>();
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_ALL_WHO_LIKED)) {
            statement.setLong(1, articleId);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                result.add(Mapper.mapUser(resultSet));
            }
        } finally {
            closeQuietly(resultSet);
        }
        return result;
    }

    private Set<Comment> getAllComments(Long articleId) throws SQLException {
        ResultSet resultSet = null;
        Set<Comment> result = new HashSet<>();
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_ALL_COMMENTS)) {
            statement.setLong(1, articleId);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                result.add(Mapper.mapComment(resultSet));
            }
        } finally {
            closeQuietly(resultSet);
        }
        return result;
    }

    @Override
    public void addLike(Long articleId, Long userId) throws SQLException {
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(INSERT_LIKE)) {

            statement.setLong(1, articleId);
            statement.setLong(2, userId);

            statement.executeUpdate();

        }
    }

    @Override
    public int deleteLike(Long articleId, Long userId) throws SQLException {
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_LIKE)) {

            statement.setLong(1, articleId);
            statement.setLong(2, userId);

            return statement.executeUpdate();
        }
    }

    @Override
    public boolean findLike(Long articleId, Long userId) throws SQLException {
        ResultSet resultSet = null;
        boolean result = false;
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_LIKE)) {

            statement.setLong(1, articleId);
            statement.setLong(2, userId);

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
    public int updateLikeInArticle(Long articleId, boolean isLiked) throws SQLException {
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_LIKE)) {

            Article article = read(articleId)
                    .orElseThrow(() -> new RuntimeException("unknown article"));

            if (isLiked) {
                statement.setLong(1, article.getLikes() - 1);
            } else {
                statement.setLong(1, article.getLikes() + 1);
            }
            statement.setLong(2, articleId);
            return statement.executeUpdate();
        }
    }

    @Override
    public void addDislike(Long articleId, Long userId) throws SQLException {
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(INSERT_DISLIKE)) {

            statement.setLong(1, articleId);
            statement.setLong(2, userId);

            statement.executeUpdate();

        }
    }

    @Override
    public int deleteDislike(Long articleId, Long userId) throws SQLException {
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_DISLIKE)) {

            statement.setLong(1, articleId);
            statement.setLong(2, userId);

            return statement.executeUpdate();
        }
    }

    @Override
    public boolean findDislike(Long articleId, Long userId) throws SQLException {
        ResultSet resultSet = null;
        boolean result = false;
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_DISLIKE)) {

            statement.setLong(1, articleId);
            statement.setLong(2, userId);

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
    public int updateDislikeInArticle(Long articleId, boolean isLiked) throws SQLException {
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_DISLIKE)) {

            Article article = read(articleId)
                    .orElseThrow(() -> new RuntimeException("unknown article"));

            if (isLiked) {
                statement.setLong(1, article.getDislikes() - 1);
            } else {
                statement.setLong(1, article.getDislikes() + 1);
            }
            statement.setLong(2, articleId);
            return statement.executeUpdate();
        }
    }


}
