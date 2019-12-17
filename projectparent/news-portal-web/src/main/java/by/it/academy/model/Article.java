package by.it.academy.model;

import lombok.*;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

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
    private Long author_id;
    private Date date;
    private Long likes;
    private Long dislikes;
    private List<Comment> comments;

    public Article(Long id, Section section, String title, String text, User author,
                   Long likes, Long dislikes, ArrayList<Comment> comments) {
        this.id = id;
        this.section = section;
        this.title = title;
        this.text = text;
        this.author = author;
        this.author_id = author.getId();
        date = new Date();
        this.likes = likes;
        this.dislikes = dislikes;
        this.comments = comments;
    }

    public void like(){
        setLikes(getLikes() + 1);
    }

    public void dislike(){
        setDislikes(getDislikes() + 1);
    }

    public void removeLike(){
        setLikes(getLikes() - 1);
    }

    public void removeDislike(){
        setDislikes(getDislikes() - 1);
    }

}
