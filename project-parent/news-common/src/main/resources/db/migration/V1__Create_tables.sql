CREATE TABLE role(
                   id          INT           NOT NULL PRIMARY KEY,
                   role_name        VARCHAR (10)  NOT NULL
);

CREATE TABLE user(
                   id          BIGINT        PRIMARY KEY NOT NULL AUTO_INCREMENT,
                   username    VARCHAR (30)  NOT NULL UNIQUE,
                   password    VARCHAR (255) NOT NULL,
                   salt        VARCHAR (255) NOT NULL,
                   role_id     INT           NOT NULL DEFAULT 3,
                   FOREIGN KEY (role_id) REFERENCES role(id)
);

ALTER TABLE user AUTO_INCREMENT=101;

CREATE TABLE section(
                      id          BIGINT        PRIMARY KEY NOT NULL AUTO_INCREMENT,
                      section_name        VARCHAR (30)  NOT NULL UNIQUE
);

CREATE TABLE article(
                      id          BIGINT        PRIMARY KEY NOT NULL AUTO_INCREMENT,
                      title       VARCHAR(255)  NOT NULL,
                      section_id  BIGINT        NOT NULL,
                      author_id   BIGINT        NOT NULL,
                      date        DATETIME      NOT NULL,
                      text        TEXT          NOT NULL,
                      likes       INT           NOT NULL DEFAULT 0,
                      FOREIGN KEY (section_id) REFERENCES section(id),
                      FOREIGN KEY (author_id) REFERENCES user(id)
);

CREATE TABLE comment(
                      id          BIGINT        PRIMARY KEY NOT NULL AUTO_INCREMENT,
                      user_id     BIGINT        NOT NULL,
                      text        VARCHAR(255)  NOT NULL,
                      date        DATETIME      NOT NULL,
                      likes       INT           NOT NULL,
                      article_id  BIGINT        NOT NULL,
                      FOREIGN KEY (user_id) REFERENCES user(id),
                      FOREIGN KEY (article_id) REFERENCES article(id) ON DELETE CASCADE
);

INSERT INTO section (section_name) VALUES
('People'),
('Technology'),
('Politics'),
('Entertainment'),
('Game'),
('World'),
('Education');


INSERT INTO role (id, role_name) VALUES
(1, 'admin'),
(2, 'author'),
(3, 'user');


INSERT INTO user (id, username, password, salt, role_id) VALUES
(1,'admin', '2217d21c39f7b03316833358edba0a522bd2d1bd376d72e9e9b5e508b78d587e', '4qnkWutZlrTTbYbLfIkDJwIXzvg=', 1),
(2,'author', '351862203494b4ff881b5eeef3a44d65ec2463814eaa597d6a7fbb0243a9905e', 'xDInfnnz9Wv9l1CA8VdD6wQBB4s=', 2),
(3,'user', 'e6ca99ed04eb30b5039455929ec4541e0da075df9cd0b6feee033bd927401e17', '6P0x9K1p9Krle17iS2Y7GnMiRcM=', 3);

