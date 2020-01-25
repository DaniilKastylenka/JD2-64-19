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

    //one-to-many +
    private User user;

    private String text;
    private Date date;
    private Long numberOfLikes;

    //one-to-many +
    private Article article;

    //many-to-many +
    private Set<User> usersWhoLiked;

    public Comment(User user, String text, Article article) {
        this.user = user;
        this.text = text;
        this.article = article;
        this.date = new Date();
        this.numberOfLikes = 0L;
    }

}
