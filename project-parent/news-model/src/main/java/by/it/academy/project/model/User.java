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

    //many-to-one
    private Role role;

    //one-to-many
    @ToString.Exclude
    private Set<Article> ownArticles;

    //many-to-many
    @ToString.Exclude
    private Set<Article> likedArticles;

    //one-to-many
    @ToString.Exclude
    private Set<Comment> ownComments;

    //many-to-many
    @ToString.Exclude
    private Set<Comment> likedComments;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
        this.role = new Role("user");
    }

    public User(String username, String password, Role role) {
        this(username, password);
        this.role = role;
    }

    public User(Long id, String username, String password, Role role) {
        this(username, password, role);
        this.id = id;
    }

    public User(Long id, String username, String password, String salt, Role role) {
        this(id, username, password, role);
        this.salt = salt;
    }

    public User(Long id, String username) {
        this.id = id;
        this.username = username;
    }
}

