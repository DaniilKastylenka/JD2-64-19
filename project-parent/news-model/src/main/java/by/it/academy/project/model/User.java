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
    private String salt = EncryptUtils.generateSalt();
    private Role role;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
        this.role = new Role(3, "user");
    }

    public User(String username, String password, Role role) {
        this(username, password);
        this.role = role;
    }

    public User(Long id, String username, String password, Role role) {
        this(username, password, role);
        this.id = id;
    }


}

