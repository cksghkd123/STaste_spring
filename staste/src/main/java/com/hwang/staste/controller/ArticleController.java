package com.hwang.staste.controller;

import com.hwang.staste.model.entity.Article;
import com.hwang.staste.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*",allowedHeaders = "*") // CorsConfig 만들었기때문에 지워도 됨
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

    @DeleteMapping("/article/{id}")
    public void deleteArticle(@PathVariable Long id) {
        articleService.deleteArticle(id);
        System.out.print(id);
    }

}
