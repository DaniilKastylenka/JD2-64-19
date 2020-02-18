package by.it.academy.project.dao;

import by.it.academy.project.model.*;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

public class Mapper {

    public static Article mapArticle(ResultSet resultSet) throws SQLException {

        Long articleId = resultSet.getLong("A_id");

        Section section = mapSection(resultSet);

        String title = resultSet.getString("A_title");

        String text = resultSet.getString("A_text");

        User author = mapUser(resultSet);

        Timestamp timestamp = resultSet.getTimestamp("A_publication_date");
        Date publicationDate = new Date(timestamp.getTime());

        Timestamp timestamp1 = resultSet.getTimestamp("A_updated_date");
        Date updatedDate = null;
        if (timestamp1 != null) {
            updatedDate = new Date(timestamp1.getTime());
        }

        Long likes = resultSet.getLong("A_likes");

        Long dislikes = resultSet.getLong("A_dislikes");

        return new Article(articleId, section, title, text, author, publicationDate, updatedDate, likes, dislikes);
    }

    public static User mapUser(ResultSet resultSet) throws SQLException {
        Long userId = resultSet.getLong("U_id");
        String username = resultSet.getString("U_username");
        String password = resultSet.getString("U_password");
        String salt = resultSet.getString("U_salt");
        Role role = mapRole(resultSet);
        return new User(userId, username, password, salt, role);
    }

    public static Comment mapComment(ResultSet resultSet) throws SQLException {
        Long id = resultSet.getLong("C_id");

        User user = mapUser(resultSet);

        String text = resultSet.getString("C_text");

        Timestamp timestamp = resultSet.getTimestamp("C_date");
        Date date = new Date(timestamp.getTime());

        Long likes = resultSet.getLong("C_likes");

        Long dislikes = resultSet.getLong("C_dislikes");

        Article article = mapArticle(resultSet);

        return new Comment(id, user, text, date, likes, dislikes, article);
    }

    public static Section mapSection(ResultSet resultSet) throws SQLException {
        Integer id = resultSet.getInt("S_id");
        String name = resultSet.getString("S_name");
        return new Section(id, name);
    }

    public static Role mapRole(ResultSet resultSet) throws SQLException {
        Integer id = resultSet.getInt("R_id");
        String name = resultSet.getString("R_name");
        return new Role(id, name);
    }

}
