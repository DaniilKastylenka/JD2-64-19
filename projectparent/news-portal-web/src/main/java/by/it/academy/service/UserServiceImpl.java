package by.it.academy.service;

import by.it.academy.model.User;

import javax.servlet.annotation.WebServlet;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

import static java.util.Objects.nonNull;

public class UserServiceImpl implements UserService {

    private static final UserService INSTANCE = new UserServiceImpl();
    private final Map<String, User> users = new ConcurrentHashMap<>();

    private AtomicLong id = new AtomicLong();

    private UserServiceImpl() {
        users.put("admin", new User(id.incrementAndGet(), "admin", "admin", "admin"));
        users.put("author", new User(id.incrementAndGet(), "author", "author", "author"));
        users.put("author1", new User(id.incrementAndGet(), "author1", "author1", "author"));
    }

    @Override
    public Optional<User> findUser(String username, String password) {
        User user = users.get(username);
        if (user != null && user.getPassword().equals(password)) {
            return Optional.of(user);
        }
        return Optional.empty();
    }

    @Override
    public boolean findUserByName(String username) {
        return nonNull(users.get(username));
    }

    @Override
    public void addUser(User user) {
        user.setId(id.incrementAndGet());
        users.put(user.getUsername(), user);
    }

    public static UserService getINSTANCE() {
        return INSTANCE;
    }
}
