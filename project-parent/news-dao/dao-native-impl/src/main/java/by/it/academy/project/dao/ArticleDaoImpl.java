package by.it.academy.project.dao;

import by.it.academy.project.dao.mapping.EntityMapping;
import by.it.academy.project.dao.mapping.EntityMappingImpl;
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

    private EntityMapping entityMapping = EntityMappingImpl.getINSTANCE();

    private static ArticleDaoImpl INSTANCE = new ArticleDaoImpl();

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
                    "JOIN section s ON a.A_id = s.S_id " +
                    "JOIN user u ON a.A_author_id = u.U_id " +
                    "JOIN role r ON u.U_role_id = r.R_id ORDER BY a.A_id DESC;";


    private static final String SELECT_ALL_WHO_LIKED =
            "SELECT * FROM like_on_article l " +
                    "JOIN user u ON l.L_user_id = u.U_id " +
                    "JOIN role r ON u.U_role_id = r.R_id WHERE L_article_id = ?;";


    private static final String SELECT_ALL_COMMENTS =
            "SELECT *  FROM comment c " +
                    "JOIN user u ON c.C_user_id = u.U_id " +
                    "JOIN article a ON c.C_article_id = a.A_id WHERE c.C_article_id = ?;";


    private static final String INSERT_LIKE =
            "INSERT INTO like_on_article (L_article_id, L_user_id)VALUE (?,?);";

    private static final String DELETE_LIKE =
            "DELETE FROM like_on_article WHERE L_article_id = ? AND L_user_id = ?;";

    private static final String FIND_LIKE =
            "SELECT * FROM like_on_article WHERE L_article_id = ? AND L_user_id = ?;";

    private static final String UPDATE_LIKE =
            "UPDATE article SET A_number_of_likes = ? WHERE A_id = ?;";


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
                result = Optional.of(entityMapping.mapArticle(resultSet));
            }

            Set<User> usersWhoLiked = getUsersWhoLiked(id);
            result.ifPresent(article -> article.setUsersWhoLiked(usersWhoLiked));

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
                articles.add(entityMapping.mapArticle(resultSet));
            }

        } finally {
            closeQuietly(resultSet);
        }

        return articles;
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
                result.add(entityMapping.mapUser(resultSet));
            }
        } finally {
            closeQuietly(resultSet);
        }
        return result;
    }

    @Override
    public Set<Comment> getAllComments(Long articleId) {
        return null;
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
