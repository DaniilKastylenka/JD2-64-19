package by.it.academy.project.dao.mapping;

import by.it.academy.project.model.*;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface EntityMapping {

    Article mapArticle(ResultSet resultSet) throws SQLException;
    User mapUser(ResultSet resultSet) throws SQLException;
    Comment mapComment(ResultSet resultSet) throws SQLException;
    Section mapSection(ResultSet resultSet) throws SQLException;
    Role mapRole(ResultSet resultSet) throws SQLException;

}
