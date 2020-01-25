package by.it.academy.project.model;

import lombok.*;

import java.util.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString

public class Article {

    private Long id;
    private Section section;
    private String title;
    private String text;
    private User author;
    private Date date;
    private Long numberOfLikes;

    public Article(Long id, String title, Section section, User author, String text) {
        this.id = id;
        this.title = title;
        this.section = section;
        this.text = text;
        this.author = author;
        this.date = new Date();
        this.numberOfLikes = 0L;
    }

    public Article(Long id, String title, Section section, User author, Date date, String text, Long likes) {
        this(id, title, section, author, text);
        this.numberOfLikes = likes;
        this.date = date;
    }


}
