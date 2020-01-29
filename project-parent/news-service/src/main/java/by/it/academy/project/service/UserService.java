package by.it.academy.project.service;

import by.it.academy.project.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    Optional<User> findUserByUsernameAndPassword(String username, String password);

    void addUser(User user);

    boolean findUserByUsername(String username);

    Optional<User> findUserByID(Long id);

    void deleteUser (Long id);

    void updateUser (User user);

    List<User> getAllUsers();


}
