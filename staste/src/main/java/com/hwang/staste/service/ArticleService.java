package com.hwang.staste.service;

import com.hwang.staste.model.entity.Article;

import java.util.List;

public interface ArticleService {
    List<Article> getArticles();

    Article postArticle(Article article);
}
