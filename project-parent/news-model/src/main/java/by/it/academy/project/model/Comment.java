package by.it.academy.project.model;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString

public class Comment {

    private Long id;
    private User user;
    private String text;
    private Date date;
    private Long likes;
    private Long article_id;

    public Comment(User user, String text, Long article_id) {
        this.user = user;
        this.text = text;
        this.article_id = article_id;
        this.date = new Date();
        this.likes = 0L;
    }

}
