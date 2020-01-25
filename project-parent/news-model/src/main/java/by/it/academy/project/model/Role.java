package by.it.academy.project.model;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString

public class Role {

    private Integer id;
    private String name;

    public Role(String name) {
        this.name = name;
    }
}
