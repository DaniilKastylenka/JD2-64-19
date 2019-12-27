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
    private Long section_id;
    private String title;
    private String text;
    private User author;
    private Long author_id;
    private Date date;
    private Long likes;
    private Long dislikes;

    public Article(Long id, String title, Section section, User author, String text) {
        this.id = id;
        this.title = title;
        this.section = section;
        this.section_id = section.getId();
        this.text = text;
        this.author = author;
        this.author_id = author.getId();
        date = new Date();
        this.likes = 0L;
        this.dislikes = 0L;
    }

    public Article(Long id, String title, Section section, User author, Date date, String text, Long likes, Long dislikes) {
        this(id, title, section, author, text);
        this.likes = likes;
        this.dislikes = dislikes;
        this.date = date;
    }


}
