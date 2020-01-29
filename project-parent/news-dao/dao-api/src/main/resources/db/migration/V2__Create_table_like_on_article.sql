CREATE TABLE like_on_article
(
  article_id BIGINT NOT NULL,
  user_id    BIGINT NOT NULL,
  FOREIGN KEY (article_id) REFERENCES article(id) ON DELETE CASCADE ,
  FOREIGN KEY (user_id) REFERENCES user(id) ON DELETE CASCADE
);


