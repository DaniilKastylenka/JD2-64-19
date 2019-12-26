package by.it.academy.project.model;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString

public class Comment {

    private Long id;
    private User user;
    private String text;
    private Long likes;
    private Long dislikes;
    private Long article_id;

}
