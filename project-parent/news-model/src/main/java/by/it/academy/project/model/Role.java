package by.it.academy.project.model;

import lombok.*;

import java.util.Objects;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class Role {

    private Integer id;
    private String name;

    //many-to-one +
    @ToString.Exclude
    private Set<User> users;

    public Role(String name) {
        this.name = name;
        switch (name) {
            case "admin":
                id = 1;
                break;
            case "author":
                id = 2;
                break;
            case "user":
                id = 3;
                break;
            default:
                id = null;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Role role = (Role) o;
        return Objects.equals(name, role.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }


}
