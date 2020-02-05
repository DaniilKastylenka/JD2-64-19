package by.it.academy.project.model;

import lombok.*;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
@Entity
@Table(name = "article")

public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "A_id")
    @EqualsAndHashCode.Exclude
    private Long id;


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "A_section_id")
    private Section section;

    @Column(name = "A_title")
    private String title;

    @Column(name = "A_text", columnDefinition = "text")
    @ToString.Exclude
    private String text;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "A_author_id")
    private User author;

    @Column(name = "A_publication_date", updatable = false)
    private Date publicationDate;

    @Column(name = "A_updated_date", insertable = false)
    @UpdateTimestamp
    private Date updatedDate;

    @Column(name = "A_number_of_likes")
    private Long numberOfLikes;

    @ManyToMany()
    @JoinTable(name = "user_article",
            joinColumns = {@JoinColumn(name = "User_U_id")},
            inverseJoinColumns = {@JoinColumn(name = "Article_A_id")})
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Set<User> usersWhoLiked;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "article")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
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
