package by.it.academy.service;

import by.it.academy.model.Article;

import java.util.List;

public interface ArticleService {

    List<Article> getAllArticles();

    void addNewArticle(Article article);

    void deleteArticle(Long id);

    Article findArticleById(Long id);

    void update(Article article);

}
