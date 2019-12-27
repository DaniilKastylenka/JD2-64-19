package by.it.academy.project.service;

import by.it.academy.project.dao.UserDao;
import by.it.academy.project.dao.UserDaoImpl;
import by.it.academy.project.model.User;
import by.it.academy.project.security.EncryptUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.SQLException;
import java.util.Optional;

import static java.util.Objects.nonNull;

public class UserServiceImpl implements UserService {

    private static final UserService INSTANCE = new UserServiceImpl();

    private static final UserDao userDao = UserDaoImpl.getINSTANCE();

    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    private UserServiceImpl() {}

    @Override
    public Optional<User> findUserByUsernameAndPassword(String username, String password) {

        logger.debug("find user by username and password");
        Optional<User> optionalUser = Optional.empty();
        try {

            String salt = userDao.getSalt(username);
            if (nonNull(salt)) {
                optionalUser = userDao
                        .findUserByUsernameAndPassword(username, EncryptUtils.getSHA256(password, salt));
            }
            logger.debug("result" + optionalUser);


        } catch (SQLException e) {
            logger.error("error while finding user by username and password" + e);
        }

        return optionalUser;
    }

    @Override
    public boolean findUserByUsername(String username) {
        logger.debug("find user by username");
        Optional<User> optionalUser = Optional.empty();
        try {
            optionalUser = userDao.findUserByUsername(username);
            logger.debug("result" + optionalUser);
        } catch (SQLException e) {
            logger.error("error while finding user by username" + e);
        }
        return optionalUser.isPresent();
    }

    @Override
    public Optional<User> findUserByID(Long id) {
        logger.debug("find user by id");
        Optional<User> optionalUser = Optional.empty();
        try{
            optionalUser = userDao.read(id);
            logger.debug("result " + optionalUser);
        }catch (SQLException e){
            logger.error("error while finding user by id " + e);
        }
        return optionalUser;
    }

    @Override
    public void addUser(User user) {
        logger.debug("add user");
        try {
            user.setPassword(EncryptUtils
                    .getSHA256(user.getPassword(), user.getSalt()));
            Long id = userDao.save(user);
            user.setId(id);
            logger.debug("result" + id);
        } catch (SQLException e) {
            logger.error("error while adding user" + e);
        }
    }

    public static UserService getINSTANCE() {
        return INSTANCE;
    }

}
