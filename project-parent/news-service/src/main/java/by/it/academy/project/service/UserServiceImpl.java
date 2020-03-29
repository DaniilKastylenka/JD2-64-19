package by.it.academy.project.service;

import by.it.academy.project.dao.UserDao;
import by.it.academy.project.dao.UserDaoImpl;
import by.it.academy.project.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static by.it.academy.project.security.EncryptUtils.getSHA256;

public class UserServiceImpl implements UserService {

    private static final UserService INSTANCE = new UserServiceImpl();

    private static final UserDao userDao = UserDaoImpl.getINSTANCE();

    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    private UserServiceImpl() {
    }

    @Override
    public Optional<User> findUserByUsernameAndPassword(String username, String password) {
        logger.debug("find user by username and password");
        try {
            Optional<User> optionalUser = userDao.findUserByUsername(username);
            if (optionalUser.isPresent()) {
                User user = optionalUser.get();
                if (user.getPassword().equals(getSHA256(password, user.getSalt()))) {
                    return Optional.of(user);
                }
            }
            logger.debug("result" + optionalUser);
        } catch (SQLException e) {
            logger.error("error while finding user by username and password", e);
        }
        return Optional.empty();
    }

    @Override
    public boolean findUserByUsername(String username) {
        logger.debug("find user by username");
        Optional<User> optionalUser = Optional.empty();
        try {
            optionalUser = userDao.findUserByUsername(username);
            logger.debug("result" + optionalUser);
        } catch (SQLException e) {
            logger.error("error while finding user by username", e);
        }
        return optionalUser.isPresent();
    }

    @Override
    public void deleteUser(Long id) {
        logger.debug("delete user by id");
        int result;
        try {
            result = userDao.delete(id);
            logger.debug("result " + result);
        } catch (SQLException e) {
            logger.error("error while deleting user", e);
        }
    }

    @Override
    public void updateUser(User user) {
        logger.debug("update user");
        try {
            userDao.update(user);
            logger.debug("result " + user.getId());
        } catch (SQLException e) {
            logger.error("error while updating user ", e);
        }
    }

    @Override
    public List<User> getAllUsers() {
        logger.debug("get all users");
        List<User> users = new ArrayList<>();
        try {
            users = userDao.getAll();
            logger.debug("result " + users);
        } catch (SQLException e) {
            logger.error("error while getting all users", e);
        }
        return users;
    }

    @Override
    public void addUser(User user) {
        logger.debug("add user");
        try {
            user.setPassword(getSHA256(user.getPassword(), user.getSalt()));
            Long id = userDao.create(user);
            user.setId(id);
            logger.debug("result" + id);
        } catch (SQLException e) {
            logger.error("error while adding user", e);
        }
    }

    public static UserService getINSTANCE() {
        return INSTANCE;
    }

}
