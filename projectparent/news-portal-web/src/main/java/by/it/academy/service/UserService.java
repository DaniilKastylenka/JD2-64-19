package by.it.academy.service;

import by.it.academy.model.User;

import java.util.Optional;

public interface UserService {

    Optional<User> findUser(String username, String password);

    void addUser(User user);

    boolean findUserByName(String username);

}
