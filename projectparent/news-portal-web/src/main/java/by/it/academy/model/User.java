package by.it.academy.model;

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


    User(String username, String password) {
        this.username = username;
        this.password = password;
        role = "user";
    }
}

