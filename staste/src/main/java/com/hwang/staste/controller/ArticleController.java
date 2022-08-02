package com.hwang.staste.controller;

import com.hwang.staste.model.entity.Article;
import com.hwang.staste.service.ArticleService;
import com.hwang.staste.service.impl.ArticleServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*",allowedHeaders = "*")
public class ArticleController {
    private final ArticleService articleService;

    @Autowired
    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @GetMapping("/")
    public String getHello() {
        return "hello";
    }

    @GetMapping("/articles")
    public List<Article> getArticles() {
        return articleService.getArticles();
    }

    @PostMapping("/article")
    public Article postArticle(@RequestBody Article article){
        return articleService.postArticle(article);
    }

}
