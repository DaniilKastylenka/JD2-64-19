package by.it.academy.service;

import by.it.academy.type.Article;

import java.util.List;

public interface ArticleService {

    List<Article> getAllArticles();
    void addNewArticle(Article article);



}
