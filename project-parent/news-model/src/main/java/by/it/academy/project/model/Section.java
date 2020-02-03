package by.it.academy.project.model;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@Entity
@Table(name = "section")

public class Section {

    @Id
    @Column(name = "S_id")
    @EqualsAndHashCode.Exclude
    private Integer id;

    @Column(name = "S_name")
    private String name;

    @OneToMany(mappedBy = "section", fetch = FetchType.LAZY)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Set<Article> articles;

    public Section(String name) {
        this.name = name;
    }

    public Section(Integer id) {
        this.id = id;
    }

    public Section(Integer id, String name) {
        this.id = id;
        this.name = name;
    }
}

