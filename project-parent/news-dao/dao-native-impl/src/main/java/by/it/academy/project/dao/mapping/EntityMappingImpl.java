package by.it.academy.project.dao.mapping;

import by.it.academy.project.model.*;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

public class EntityMappingImpl implements EntityMapping {

    private static final EntityMapping INSTANCE = new EntityMappingImpl();

    public EntityMappingImpl(){ }

    @Override
    public Article mapArticle(ResultSet resultSet) throws SQLException {

        Long article_id = resultSet.getLong("A_id");

        Section section = mapSection(resultSet);

        String title = resultSet.getString("A_title");

        String text = resultSet.getString("A_text");

        User author = mapUser(resultSet);

        Timestamp timestamp = resultSet.getTimestamp("A_publication_date");
        Date publicationDate = new Date(timestamp.getTime());

        Timestamp timestamp1 = resultSet.getTimestamp("A_updated_date");
        java.util.Date updatedDate = null;
        if (timestamp1 != null) {
            updatedDate = new Date(timestamp1.getTime());
        }

        Long numberOfLikes = resultSet.getLong("A_number_of_likes");

        return new Article(article_id, section, title, text, author, publicationDate, updatedDate, numberOfLikes);
    }

    @Override
    public User mapUser(ResultSet resultSet) throws SQLException {
        Long userId = resultSet.getLong("U_id");
        String username = resultSet.getString("U_username");
        String password = resultSet.getString("U_password");
        String salt = resultSet.getString("U_salt");
        Role role = mapRole(resultSet);
        return new User(userId, username, password, salt, role);
    }

    @Override
    public Comment mapComment(ResultSet resultSet) throws SQLException {
        Long id = resultSet.getLong("C_id");

        User user = mapUser(resultSet);

        String text = resultSet.getString("C_text");

        Timestamp timestamp = (Timestamp) resultSet.getObject("C_date");
        java.util.Date date = new java.util.Date(timestamp.getTime());

        Long likes = resultSet.getLong("C_number_of_likes");

        Long article_id = resultSet.getLong("C_article_id");

        return new Comment(id, user, text, date, likes, new Article(article_id));
    }

    @Override
    public Section mapSection(ResultSet resultSet) throws SQLException {
        Long id = resultSet.getLong("S_id");
        String name = resultSet.getString("S_name");
        return new Section(id, name);
    }

    @Override
    public Role mapRole(ResultSet resultSet) throws SQLException {
        Integer id = resultSet.getInt("R_id");
        String name = resultSet.getString("R_name");
        return new Role(id, name);
    }

    public static EntityMapping getINSTANCE() {
        return INSTANCE;
    }
}
