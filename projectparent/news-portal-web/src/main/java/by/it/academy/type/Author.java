package by.it.academy.type;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class Author {

    private String nickname;

    public Author(String nickname) {
        this.nickname = nickname;
    }

}
