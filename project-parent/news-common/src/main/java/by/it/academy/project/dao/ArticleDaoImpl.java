package by.it.academy.project.dao;

import by.it.academy.project.model.Article;
import by.it.academy.project.model.Section;
import by.it.academy.project.model.User;
import by.it.academy.project.service.SectionServiceImpl;
import by.it.academy.project.service.UserServiceImpl;
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
            "SELECT * FROM article WHERE id=?;";

    private static final String UPDATE_ARTICLE =
            "UPDATE article SET title = ?, section_id = ?, author_id = ?, text = ? WHERE id=?;";

    private static final String DELETE_ARTICLE =
            "DELETE FROM article WHERE id = ?;";

    private static final String SELECT_ALL_ARTICLES =
            "SELECT * FROM article;";


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

        Section section = SectionServiceImpl.getINSTANCE().findSectionByID(resultSet.getLong("section_id"));

        User author = UserServiceImpl.getINSTANCE()
                .findUserByID(resultSet.getLong("author_id"))
                .orElseThrow(()-> new RuntimeException("error map article, unknown author"));

        Timestamp timestamp = (Timestamp) resultSet.getObject("date");
        Date date = new Date(timestamp.getTime());

        String text = resultSet.getString("text");

        Long likes = resultSet.getLong("likes");

        Long dislikes = resultSet.getLong("dislikes");

        return new Article(article_id, title, section, author, date, text, likes, dislikes);
    }
}
