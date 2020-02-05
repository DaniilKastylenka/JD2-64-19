package by.it.academy.project.service;

import by.it.academy.project.model.Article;

import java.util.List;
import java.util.Optional;

public interface ArticleService {

    List<Article> getAllArticles();

    void addNewArticle(Article article);

    void deleteArticle(Long id);

    Optional<Article> findArticleById(Long id);

    void update(Article article);

    void like(Long articleId, Long userId);

    void dislike(Long articleId, Long userId);

}
