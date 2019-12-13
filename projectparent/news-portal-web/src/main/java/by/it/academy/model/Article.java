package by.it.academy.model;

import lombok.*;

import java.util.*;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
@ToString

public class Article {

    private Long id;
    private String section;
    private String title;
    private String text;
    private Long author_id;
    private Date date;
    private Long likes;
    private Long dislikes;
    private List<Comment> comments;

    public Article(Long id, String section, String title, String text, User user,
                   Long likes, Long dislikes, ArrayList<Comment> comments) {
        this.id = id;
        this.section = section;
        this.title = title;
        this.text = text;
        this.author_id = user.getId();
        date = new Date();
        this.likes = likes;
        this.dislikes = dislikes;
        this.comments = comments;
    }

}
