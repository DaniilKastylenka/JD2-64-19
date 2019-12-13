package by.it.academy.service;

import by.it.academy.model.User;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

public class UserServiceImpl implements UserService {

    private static final UserService INSTANCE = new UserServiceImpl();
    private final Map<String, User> users = new ConcurrentHashMap<>();

    private AtomicLong id = new AtomicLong();

    private UserServiceImpl(){
        users.put("admin", new User(1L,"admin", "admin","admin"));
    }

    @Override
    public Optional<User> findUser(String username, String password) {
        User user = users.get(username);
        if (user != null && user.getPassword().equals(password)){
            return Optional.of(user);
        }
        return Optional.empty();
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
