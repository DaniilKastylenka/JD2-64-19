package by.it.academy.type;

import lombok.Getter;
import lombok.Setter;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
public class Article {

    private Long id;
    private String title;
    private String text;
    private Author author;
    private String publicationDate;
    private Long likes;
    private Long dislikes;
    private List<Comment> comments;

    public Article(Long id, String title, String text, Author author,
                   Long likes, Long dislikes, ArrayList<Comment> comments) {
        this.id = id;
        this.title = title;
        this.text = text;
        this.author = author;
        publicationDate = DateFormat.getDateInstance(DateFormat.FULL).format(new Date());
        this.likes = likes;
        this.dislikes = dislikes;
        this.comments = comments;
    }

    @Override
    public String toString() {
        return "Article{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", text='" + text + '\'' +
                ", author=" + author +
                ", publicationDate='" + publicationDate + '\'' +
                ", likes=" + likes +
                ", dislikes=" + dislikes +
                ", comments=" + comments +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Article article = (Article) o;
        return Objects.equals(id, article.id) &&
                Objects.equals(title, article.title) &&
                Objects.equals(text, article.text) &&
                Objects.equals(author, article.author) &&
                Objects.equals(publicationDate, article.publicationDate) &&
                Objects.equals(likes, article.likes) &&
                Objects.equals(dislikes, article.dislikes) &&
                Objects.equals(comments, article.comments);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, text, author, publicationDate, likes, dislikes, comments);
    }
}
