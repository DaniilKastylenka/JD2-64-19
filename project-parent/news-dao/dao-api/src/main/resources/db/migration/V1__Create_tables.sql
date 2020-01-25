CREATE TABLE role(
                   R_id          INT           NOT NULL PRIMARY KEY,
                   R_name        VARCHAR (10)  NOT NULL
);

CREATE TABLE user(
                   U_id          BIGINT        PRIMARY KEY NOT NULL AUTO_INCREMENT,
                   U_username    VARCHAR (30)  NOT NULL UNIQUE,
                   U_password    VARCHAR (255) NOT NULL,
                   U_salt        VARCHAR (255) NOT NULL,
                   U_role_id     INT           NOT NULL DEFAULT 3,
                   FOREIGN KEY (U_role_id) REFERENCES role(R_id)
);

ALTER TABLE user AUTO_INCREMENT=101;

CREATE TABLE section(
                      S_id          BIGINT        PRIMARY KEY NOT NULL AUTO_INCREMENT,
                      S_name        VARCHAR (30)  NOT NULL UNIQUE
);

CREATE TABLE article(
                      A_id                BIGINT        PRIMARY KEY NOT NULL AUTO_INCREMENT,
                      A_title             VARCHAR(255)  NOT NULL,
                      A_section_id        BIGINT        NOT NULL,
                      A_author_id         BIGINT        NOT NULL DEFAULT 0,
                      A_publication_date  DATETIME      NOT NULL,
                      A_updated_date      DATETIME      DEFAULT NULL,
                      A_text              TEXT          NOT NULL,
                      A_number_of_likes   INT           NOT NULL DEFAULT 0,
                      FOREIGN KEY (A_section_id) REFERENCES section(S_id),
                      FOREIGN KEY (A_author_id) REFERENCES user(U_id) ON DELETE CASCADE
);

CREATE TABLE comment(
                      C_id              BIGINT        PRIMARY KEY NOT NULL AUTO_INCREMENT,
                      C_user_id         BIGINT        NOT NULL,
                      C_text            VARCHAR(255)  NOT NULL,
                      C_date            DATETIME      NOT NULL,
                      C_number_of_likes INT           NOT NULL,
                      C_article_id      BIGINT        NOT NULL,
                      FOREIGN KEY (C_user_id) REFERENCES user(U_id) ON DELETE CASCADE ,
                      FOREIGN KEY (C_article_id) REFERENCES article(A_id) ON DELETE CASCADE
);

CREATE TABLE like_on_article
(
                      L_article_id      BIGINT        NOT NULL,
                      L_user_id         BIGINT        NOT NULL,
                      FOREIGN KEY (L_article_id) REFERENCES article(A_id) ON DELETE CASCADE ,
                      FOREIGN KEY (L_user_id) REFERENCES user(U_id) ON DELETE CASCADE
);

INSERT INTO section (S_name) VALUES
('People'),
('Technology'),
('Politics'),
('Entertainment'),
('Game'),
('World'),
('Education');


INSERT INTO role (R_id, R_name) VALUES
(1, 'admin'),
(2, 'author'),
(3, 'user');


INSERT INTO user (U_id, U_username, U_password, U_salt, U_role_id) VALUES
(1,'admin', '2217d21c39f7b03316833358edba0a522bd2d1bd376d72e9e9b5e508b78d587e', '4qnkWutZlrTTbYbLfIkDJwIXzvg=', 1),
(2,'author', '351862203494b4ff881b5eeef3a44d65ec2463814eaa597d6a7fbb0243a9905e', 'xDInfnnz9Wv9l1CA8VdD6wQBB4s=', 2),
(3,'user', 'e6ca99ed04eb30b5039455929ec4541e0da075df9cd0b6feee033bd927401e17', '6P0x9K1p9Krle17iS2Y7GnMiRcM=', 3);

