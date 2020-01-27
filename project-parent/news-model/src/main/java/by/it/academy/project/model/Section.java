package by.it.academy.project.model;

import lombok.*;

import java.util.Objects;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class Section {

    private Integer id;
    private String name;

    //many-to-one +
    @ToString.Exclude
    private Set<Article> articles;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Section section = (Section) o;
        return Objects.equals(name, section.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    public Section(String name) {
        this.name = name;
        switch (name) {
            case "People":
                id = 1;
                break;
            case "Technology":
                id = 2;
                break;
            case "Politics":
                id = 3;
                break;
            case "Entertainment":
                id = 4;
                break;
            case "Game":
                id = 5;
                break;
            case "World":
                id = 6;
                break;
            case "Education":
                id = 7;
                break;
            default:
                id = null;
        }



    }
}
