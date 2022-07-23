package com.hwang.staste.repository;

import com.hwang.staste.model.entity.Article;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleRepository extends JpaRepository<Article, Long>{

}
