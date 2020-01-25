package by.it.academy.project.model;

import by.it.academy.project.security.EncryptUtils;
import lombok.*;

import java.util.Set;

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

    //one-to-many +
    private Role role;

    //many-to-one +
    private Set<Article> ownArticles;

    //many-to-many +
    private Set<Article> likedArticles;

    //many-to-one +
    private Set<Comment> ownComments;

    //many-to-many +
    private Set<Comment> likedComments;

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

