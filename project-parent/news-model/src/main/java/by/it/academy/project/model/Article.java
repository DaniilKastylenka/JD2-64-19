package by.it.academy.project.model;

import lombok.*;

import java.util.*;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
@ToString

public class Article {

    private Long id;
    private Section section;
    private String title;
    private String text;
    private User author;
    private Date publicationDate;
    private Long numberOfLikes;

    public Article(Long id, Section section, String title, String text, User author) {
        this.id = id;
        this.title = title;
        this.section = section;
        this.text = text;
        this.author = author;
        this.publicationDate = new Date();
        this.numberOfLikes = 0L;
    }

    public Article(Long id, Section section, String title, String text, User author, Date publicationDate, Long likes) {
        this(id, section, title, text, author);
        this.numberOfLikes = likes;
        this.publicationDate = publicationDate;
    }


}
