package by.it.academy.project.model;

import by.it.academy.project.security.EncryptUtils;
import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
@Entity
@Table(name = "user")


public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "U_id")
    @EqualsAndHashCode.Exclude
    private Long id;

    @Column(name = "U_username")
    private String username;

    @Column(name = "U_password")
    private String password;

    @Column(name = "U_salt")
    private String salt = EncryptUtils.generateSalt();

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "U_role_id")
    private Role role;

    @OneToMany(mappedBy = "author", fetch = FetchType.LAZY)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Set<Article> ownArticles;

    @ManyToMany(fetch = FetchType.LAZY)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @Transient
    private Set<Article> likedArticles;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Set<Comment> ownComments;

    @ManyToMany()
    @JoinTable(name = "comment_user",
            joinColumns = {@JoinColumn(name = "User_U_id")},
            inverseJoinColumns = {@JoinColumn(name = "Comment_C_id")})
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Set<Comment> likedComments;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
        this.role = new Role(3,"user");
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

    public User(Long id) {
        this.id = id;
    }
}

