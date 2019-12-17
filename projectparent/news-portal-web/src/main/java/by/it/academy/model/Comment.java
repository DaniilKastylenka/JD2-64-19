package by.it.academy.model;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString

public class Comment {

    private User user;
    private String text;
    private Long likes;
    private Long dislikes;

}
