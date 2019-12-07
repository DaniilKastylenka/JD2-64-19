package by.it.academy.type;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class Comment {

    private User user;
    private String text;
    private Long likes;
    private Long dislikes;

    public Comment(User user, String text, Long likes, Long dislikes) {
        this.user = user;
        this.text = text;
        this.likes = likes;
        this.dislikes = dislikes;
    }

    public Comment() {
    }
}
