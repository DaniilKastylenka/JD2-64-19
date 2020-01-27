package by.it.academy.project.model;

import lombok.*;

import java.util.Date;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString

public class Comment {

    private Long id;

    //one-to-many +++
    private User user;

    private String text;
    private Date date;
    private Long numberOfLikes;

    //one-to-many +++
    private Article article;

    //many-to-many +
    @ToString.Exclude
    private Set<User> usersWhoLiked;

    public Comment(User user, String text, Article article) {
        this.user = user;
        this.text = text;
        this.article = article;
        this.date = new Date();
        this.numberOfLikes = 0L;
    }

    public Comment(Long id, User user, String text, Date date, Long numberOfLikes, Article article) {
        this(user, text, article);
        this.id = id;
        this.date = date;
        this.numberOfLikes = numberOfLikes;
    }
}
