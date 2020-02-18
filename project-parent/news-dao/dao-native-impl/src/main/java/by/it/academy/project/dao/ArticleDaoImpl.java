package by.it.academy.project.dao;

import by.it.academy.project.model.Article;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    private static final String GET_ARTICLES_BY_SEARCH_REQUEST =
            "SELECT * FROM article a " +
                    "JOIN section s ON a.A_section_id = s.S_id " +
                    "JOIN user u ON a.A_author_id = u.U_id " +
                    "JOIN role r ON u.U_role_id = r.R_id WHERE a.A_title LIKE  ?  ORDER BY a.A_publication_date DESC;";

    private static final String GET_ARTICLES_BY_SEARCH_REQUEST_AND_USER_ID =
            "SELECT * FROM article a " +
                    "JOIN section s ON a.A_section_id = s.S_id " +
                    "JOIN user u ON a.A_author_id = u.U_id " +
                    "JOIN role r ON u.U_role_id = r.R_id WHERE a.A_title LIKE  ?  AND a.A_author_id = ? ORDER BY a.A_publication_date DESC;";


    private static final String SELECT_ALL_ARTICLES =
            "SELECT * FROM article a " +
                    "JOIN section s ON a.A_section_id = s.S_id " +
                    "JOIN user u ON a.A_author_id = u.U_id " +
                    "JOIN role r ON u.U_role_id = r.R_id ORDER BY a.A_publication_date DESC;";

    private static final String SELECT_LIMITED_NUMBER_OF_ARTICLES =
            "SELECT * FROM article a " +
                    "JOIN section s ON a.A_section_id = s.S_id " +
                    "JOIN user u ON a.A_author_id = u.U_id " +
                    "JOIN role r ON u.U_role_id = r.R_id ORDER BY a.A_publication_date DESC LIMIT ?, ?;";

    private static final String GET_COUNT_OF_ARTICLES =
            "SELECT count(a.A_id) FROM article a";

    private static final String SELECT_LIMITED_NUMBER_OF_ARTICLES_BY_SECTION_ID =
            "SELECT * FROM article a " +
                    "JOIN section s ON a.A_section_id = s.S_id " +
                    "JOIN user u ON a.A_author_id = u.U_id " +
                    "JOIN role r ON u.U_role_id = r.R_id WHERE a.A_section_id = ? ORDER BY a.A_publication_date DESC LIMIT ?, ?;";

    private static final String GET_COUNT_OF_ARTICLES_BY_SECTION_ID =
            "SELECT count(a.A_id) FROM article a WHERE a.A_section_id = ?";

    private static final String GET_LIMITED_NUMBER_OF_ARTICLES_BY_USER_ID =
            "SELECT * FROM article a " +
                    "JOIN section s ON a.A_section_id = s.S_id " +
                    "JOIN user u ON a.A_author_id = u.U_id " +
                    "JOIN role r ON u.U_role_id = r.R_id WHERE a.A_author_id = ? ORDER BY a.A_publication_date DESC LIMIT ?, ?;";

    private static final String GET_COUNT_OF_ARTICLES_BY_USER_ID =
            "SELECT count(a.A_id) FROM article a WHERE a.A_author_id = ?";

    private static final String GET_LIMITED_NUMBER_OF_ARTICLES_BY_USER_ID_AND_SECTION_ID =
            "SELECT * FROM article a " +
                    "JOIN section s ON a.A_section_id = s.S_id " +
                    "JOIN user u ON a.A_author_id = u.U_id " +
                    "JOIN role r ON u.U_role_id = r.R_id WHERE a.A_author_id = ? AND a.A_section_id = ? ORDER BY a.A_publication_date DESC LIMIT ?, ?;";

    private static final String GET_COUNT_OF_ARTICLES_BY_USER_ID_AND_SECTION_ID =
            "SELECT count(a.A_id) FROM article a WHERE a.A_author_id = ? AND a.A_section_id = ?";

    private static final String SELECT_ALL_BY_SECTION =
            "SELECT * FROM article a " +
                    "JOIN section s ON a.A_section_id = s.S_id " +
                    "JOIN user u ON a.A_author_id = u.U_id " +
                    "JOIN role r ON u.U_role_id = r.R_id WHERE a.A_section_id = ? ORDER BY a.A_publication_date DESC;";

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

    @Override
    public List<Article> getArticlesBySearchRequest(String request) throws SQLException {
        ResultSet resultSet = null;
        List<Article> result = new ArrayList<>();
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(GET_ARTICLES_BY_SEARCH_REQUEST)) {
            statement.setString(1, "%" + request + "%");
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                result.add(Mapper.mapArticle(resultSet));
            }
        } finally {
            closeQuietly(resultSet);
        }
        return result;
    }

    @Override
    public List<Article> getArticlesBySearchRequestAndUserId(String request, Long id) throws SQLException {
        ResultSet resultSet = null;
        List<Article> result = new ArrayList<>();
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(GET_ARTICLES_BY_SEARCH_REQUEST_AND_USER_ID)) {
            statement.setString(1, "%" + request + "%");
            statement.setLong(2, id);
            resultSet = statement.executeQuery();
            while(resultSet.next()){
                result.add(Mapper.mapArticle(resultSet));
            }
        } finally {
            closeQuietly(resultSet);
        }
        return result;
    }

    @Override
    public List<Article> getLimitedNumberOfArticles(int start, int total) throws SQLException {
        ResultSet resultSet = null;
        List<Article> result = new ArrayList<>();
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_LIMITED_NUMBER_OF_ARTICLES)) {
            statement.setInt(1, start - 1);
            statement.setInt(2, total);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                result.add(Mapper.mapArticle(resultSet));
            }
        } finally {
            closeQuietly(resultSet);
        }
        return result;
    }

    @Override
    public int getCountOfArticles() throws SQLException {
        ResultSet resultSet = null;
        int result = 0;
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(GET_COUNT_OF_ARTICLES)) {
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                result = resultSet.getInt(1);
            }
        } finally {
            closeQuietly(resultSet);
        }
        return result;
    }

    @Override
    public List<Article> getLimitedNumberOfArticlesBySectionId(int start, int total, int sectionId) throws SQLException {
        ResultSet resultSet = null;
        List<Article> result = new ArrayList<>();
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_LIMITED_NUMBER_OF_ARTICLES_BY_SECTION_ID)) {
            statement.setInt(1, sectionId);
            statement.setInt(2, start - 1);
            statement.setInt(3, total);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                result.add(Mapper.mapArticle(resultSet));
            }
        } finally {
            closeQuietly(resultSet);
        }
        return result;
    }

    @Override
    public int getCountOfArticlesBySectionId(int sectionId) throws SQLException {
        ResultSet resultSet = null;
        int result = 0;
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(GET_COUNT_OF_ARTICLES_BY_SECTION_ID)) {
            statement.setInt(1, sectionId);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                result = resultSet.getInt(1);
            }
        } finally {
            closeQuietly(resultSet);
        }
        return result;
    }

    @Override
    public List<Article> getLimitedNumberOfArticlesByUserId(int start, int total, Long userId) throws SQLException {
        ResultSet resultSet = null;
        List<Article> result = new ArrayList<>();
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(GET_LIMITED_NUMBER_OF_ARTICLES_BY_USER_ID)) {
            statement.setLong(1, userId);
            statement.setInt(2, start - 1);
            statement.setInt(3, total);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                result.add(Mapper.mapArticle(resultSet));
            }
        } finally {
            closeQuietly(resultSet);
        }
        return result;
    }

    @Override
    public int getCountOfArticlesByUserId(Long userId) throws SQLException {
        ResultSet resultSet = null;
        int result = 0;
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(GET_COUNT_OF_ARTICLES_BY_USER_ID)) {
            statement.setLong(1, userId);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                result = resultSet.getInt(1);
            }
        } finally {
            closeQuietly(resultSet);
        }
        return result;
    }

    @Override
    public List<Article> getLimitedNumberOfArticlesByUserIdAndSectionId(int start, int total, Long userId, int sectionId) throws SQLException {
        ResultSet resultSet = null;
        List<Article> result = new ArrayList<>();
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(GET_LIMITED_NUMBER_OF_ARTICLES_BY_USER_ID_AND_SECTION_ID)) {
            statement.setLong(1, userId);
            statement.setInt(2, sectionId);
            statement.setInt(3, start - 1);
            statement.setInt(4, total);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                result.add(Mapper.mapArticle(resultSet));
            }
        } finally {
            closeQuietly(resultSet);
        }
        return result;
    }

    @Override
    public int getCountOfArticlesByUserIdAndSectionId(Long userId, int sectionId) throws SQLException {
        ResultSet resultSet = null;
        int result = 0;
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(GET_COUNT_OF_ARTICLES_BY_USER_ID_AND_SECTION_ID)) {
            statement.setLong(1, userId);
            statement.setInt(2, sectionId);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                result = resultSet.getInt(1);
            }
        } finally {
            closeQuietly(resultSet);
        }
        return result;
    }

    @Override
    public List<Article> getAllBySectionId(int sectionId) throws SQLException {
        ResultSet resultSet = null;
        List<Article> result = new ArrayList<>();
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_ALL_BY_SECTION)) {
            statement.setInt(1, sectionId);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                result.add(Mapper.mapArticle(resultSet));
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
