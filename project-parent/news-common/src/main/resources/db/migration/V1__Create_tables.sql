CREATE TABLE role(
                   id          INT           NOT NULL PRIMARY KEY,
                   name        VARCHAR (10)  NOT NULL
);

CREATE TABLE user(
                   id          BIGINT        PRIMARY KEY NOT NULL AUTO_INCREMENT,
                   username    VARCHAR (30)  NOT NULL UNIQUE,
                   password    VARCHAR (255) NOT NULL,
                   salt        VARCHAR (255) NOT NULL,
                   role_id     INT           NOT NULL DEFAULT 3,
                   FOREIGN KEY (role_id) REFERENCES role(id)
);

CREATE TABLE section(
                      id          BIGINT        PRIMARY KEY NOT NULL AUTO_INCREMENT,
                      name        VARCHAR (30)  NOT NULL UNIQUE
);

CREATE TABLE article(
                      id          BIGINT        PRIMARY KEY NOT NULL AUTO_INCREMENT,
                      title       VARCHAR(255)  NOT NULL,
                      section_id  BIGINT        NOT NULL,
                      author_id   BIGINT        NOT NULL,
                      date        DATETIME      NOT NULL,
                      text        TEXT          NOT NULL,
                      likes       INT           NOT NULL DEFAULT 0,
                      dislikes    INT           NOT NULL DEFAULT 0,
                      FOREIGN KEY (section_id) REFERENCES section(id),
                      FOREIGN KEY (author_id) REFERENCES user(id)
);

CREATE TABLE comment(
                      id          BIGINT        PRIMARY KEY NOT NULL AUTO_INCREMENT,
                      user_id     BIGINT        NOT NULL,
                      text        VARCHAR(255)  NOT NULL,
                      date        DATETIME      NOT NULL,
                      likes       INT           NOT NULL,
                      dislikes    INT           NOT NULL,
                      article_id  BIGINT        NOT NULL,
                      FOREIGN KEY (user_id) REFERENCES user(id),
                      FOREIGN KEY (article_id) REFERENCES article(id)
);

INSERT INTO section (name) VALUES
('People'),
('Technology'),
('Politics'),
('Entertainment'),
('Game'),
('World'),
('Education');


INSERT INTO role (id, name) VALUES
(1, 'admin'),
(2, 'author'),
(3, 'user');


INSERT INTO user (username, password, salt, role_id) VALUES
('admin', 'admin', 'admin', 1),
('author', 'author', 'admin', 2),
('author1', 'author1', 'admin', 2),
('user', 'user', 'admin', 3);

