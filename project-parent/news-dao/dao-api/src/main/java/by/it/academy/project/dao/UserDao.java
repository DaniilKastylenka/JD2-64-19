package by.it.academy.project.dao;

import by.it.academy.project.model.User;

import java.sql.SQLException;
import java.util.Optional;

public interface UserDao extends DAO<User>{

    Optional<User> findUserByUsername(String username) throws SQLException;

}
