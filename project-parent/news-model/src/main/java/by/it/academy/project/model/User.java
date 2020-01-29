package by.it.academy.project.model;

import by.it.academy.project.security.EncryptUtils;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString

public class User {

    private Long id;
    private String username;
    private String password;
    private String salt;
    private String role;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
        this.salt = EncryptUtils.generateSalt();
        this.role = "user";
    }

    public User(Long id, String username, String password, String role) {
        this(username, password);
        this.id = id;
        this.role = role;
        this.salt = EncryptUtils.generateSalt();
    }
    public User(String username, String password, String role) {
        this(username, password);
        this.role = role;
        this.salt = EncryptUtils.generateSalt();
    }

}

