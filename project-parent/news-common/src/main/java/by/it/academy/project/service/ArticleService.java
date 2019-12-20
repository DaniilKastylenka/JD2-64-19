package by.it.academy.project.service;

import by.it.academy.project.model.Article;

import java.util.List;

public interface ArticleService {

    List<Article> getAllArticles();

    void addNewArticle(Article article);

    void deleteArticle(Long id);

    Article findArticleById(Long id);

    void update(Article article);

}
