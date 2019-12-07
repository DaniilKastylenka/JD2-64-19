package by.it.academy.service;

import by.it.academy.type.Author;
import by.it.academy.type.Article;

import java.util.ArrayList;
import java.util.List;

public class ArticleServiceImpl implements ArticleService {

    private static final ArticleService INSTANCE = new ArticleServiceImpl();

    private final List<Article> articles;

    public ArticleServiceImpl() {
        articles = new ArrayList<>();
    }

    @Override
    public List<Article> getAllArticles() {
        return articles;
    }

    @Override
    public void addNewArticle(Article article) {
        article.setId((long) articles.size() + 1);
        articles.add(article);
    }

    public synchronized static ArticleService getService() {
        return INSTANCE;
    }
}
