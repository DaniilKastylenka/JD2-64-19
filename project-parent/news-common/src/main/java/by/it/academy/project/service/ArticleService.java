package by.it.academy.project.service;

import by.it.academy.project.model.Article;
import by.it.academy.project.model.User;

import java.util.List;
import java.util.Optional;

public interface ArticleService {

    List<Article> getAllArticles();

    void addNewArticle(Article article);

    void deleteArticle(Long id);

    Optional<Article> findArticleById(Long id);

    void update(Article article);

    void like(Long article_id, Long user_id);

}
