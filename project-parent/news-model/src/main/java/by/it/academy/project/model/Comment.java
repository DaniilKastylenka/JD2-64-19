package by.it.academy.project.model;

import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
@Entity
@Table(name = "comment")

public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "C_id")
    @EqualsAndHashCode.Exclude
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "C_user_id")
    private User user;

    @Column(name = "C_text")
    private String text;

    @Column(name = "C_date", updatable = false)
    private Date date;

    @Column(name = "C_likes")
    private Long likes;

    @Column(name = "C_dislikes")
    private Long dislikes;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "C_article_id")
    private Article article;

    @ManyToMany(mappedBy = "likedComments")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Set<User> usersWhoLiked;

    @ManyToMany(mappedBy = "dislikedComments")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Set<User> usersWhoDisliked;

    public Comment(Long id){
        this.id = id;
    }

    public Comment(User user, String text, Article article) {
        this.user = user;
        this.text = text;
        this.article = article;
        this.date = new Date();
        this.likes = 0L;
        this.dislikes = 0L;
    }

    public Comment(Long id, User user, String text, Date date, Long likes, Long dislikes, Article article) {
        this(user, text, article);
        this.id = id;
        this.date = date;
        this.likes = likes;
        this.dislikes = dislikes;
    }
}
