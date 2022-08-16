package com.hwang.staste.repository;

import com.hwang.staste.model.entity.Article;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class ArticleRepositoryTest {
    @Autowired
    ArticleRepository articleRepository;

    @AfterEach
    public void 전부지우기(){
        articleRepository.deleteAll();
    }

    @Test
    public void 게시글_저장하기(){
        String title = "바이";
        String content = "바이킹";
        Article article = Article.builder()
                .title(title)
                .content(content)
                .build();
        articleRepository.save(article);
    }

    @Test
    public void 게시글_전부가져오기(){
        String title = "ㅁㅁㅁ";
        String content = "T^T";
        Article article = Article.builder()
                .title(title)
                .content(content)
                .build();
        articleRepository.save(article);

        List<Article> articleList = articleRepository.findAll();

        Assertions.assertThat(articleList.get(articleList.size()-1).getContent()).isEqualTo("@@@@");
        Assertions.assertThat(articleList.get(articleList.size()-1).getTitle()).isEqualTo(title);


    }
}
