package by.it.academy.project.model;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString

public class User {

    private Long id;
    private String username;
    private String password;
    private String role;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
        role = "user";
    }

    public User(String username, String password, String role) {
        this(username,password);
        this.role = role;
    }

}

