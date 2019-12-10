package by.it.academy.type;

import lombok.*;

import java.util.*;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
@ToString

public class Article {

    private Long id;
    private String title;
    private String text;
    private Author author;
    private Date date;
    private Long likes;
    private Long dislikes;
    private List<Comment> comments;

    public Article(Long id, String title, String text, Author author,
                   Long likes, Long dislikes, ArrayList<Comment> comments) {
        this.id = id;
        this.title = title;
        this.text = text;
        this.author = author;
        date = new Date();
        this.likes = likes;
        this.dislikes = dislikes;
        this.comments = comments;
    }

}
