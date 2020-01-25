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

    //one-to-many +
    private Section section;

    private String title;
    private String text;

    //one-to-many +
    private User author;
    private Date publicationDate;
    private Date updatedDate;
    private Long numberOfLikes;

    //many-to-many +
    private Set<User> usersWhoLiked;

    //many-to-one +
    private Set<Comment> comments;



    public Article(Long id, Section section, String title, String text, User author) {
        this.id = id;
        this.title = title;
        this.section = section;
        this.text = text;
        this.author = author;
        this.publicationDate = new Date();
        this.updatedDate = null;
        this.numberOfLikes = 0L;
    }

    public Article(Long id, Section section, String title, String text, User author, Date publicationDate, Date updatedDate, Long likes) {
        this(id, section, title, text, author);
        this.publicationDate = publicationDate;
        this.updatedDate = updatedDate;
        this.numberOfLikes = likes;
    }

    public Article(Long id) {
        this.id = id;
    }
}
