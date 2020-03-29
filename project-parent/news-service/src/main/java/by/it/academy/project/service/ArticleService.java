package by.it.academy.project.service;

import by.it.academy.project.model.Article;

import java.util.List;
import java.util.Optional;

public interface ArticleService {

    List<Article> getAllArticles();

    List<Article> getArticlesBySearchRequest(String request);

    List<Article> getArticlesBySearchRequestAndUserId(String request, Long id);

    List<Article> getLimitedNumberOfArticles(int start, int total);

    int getCountOfPages(int countOfArticlesOnPage);

    List<Article> getLimitedNumberOfArticlesBySectionId(int start, int total, int sectionId);

    int getCountOfPagesWithArticlesBySectionId(int countOfArticlesOnPage, int sectionId);

    List<Article> getLimitedNumberOfArticlesByUserId(int start, int total, Long userId);

    int getCountOfPagesWithArticlesByUserId(int countOfArticlesOnPage, Long userId);

    List<Article> getLimitedNumberOfArticlesByUserIdAndSectionId(int start, int total, Long userId, int sectionId);

    int getCountOfPagesWithArticlesByUserIdAndSectionId(int countOfArticlesOnPage, Long userId, int sectionId);

    List<Article> getAllBySectionId(int sectionId);

    void addNewArticle(Article article);

    void deleteArticle(Long id);

    Optional<Article> findArticleById(Long id);

    void update(Article article);

    void like(Long articleId, Long userId);

    boolean isLiked(Long articleId, Long userId);

    void dislike(Long articleId, Long userId);

    boolean isDisliked(Long articleId, Long userId);

}
