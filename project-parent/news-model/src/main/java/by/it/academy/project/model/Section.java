package by.it.academy.project.model;

import lombok.*;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString

public class Section {

    private Long id;
    private String name;

    //many-to-one +
    private Set<Article> articles;

    public Section(Long id, String name) {
        this.id = id;
        this.name = name;
    }
}
