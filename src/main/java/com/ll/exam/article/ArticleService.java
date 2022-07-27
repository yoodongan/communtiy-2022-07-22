package com.ll.exam.article;

import com.ll.exam.article.dto.ArticleDto;

import java.util.List;

public class ArticleService {
    private ArticleRepository articleRepository;

    public ArticleService() {
        articleRepository = new ArticleRepository();
    }

    public long write(String title, String body) {
        return articleRepository.write(title, body);

    }

    public List<ArticleDto> findAll() {
        return articleRepository.findAll();
    }

    public ArticleDto findById(long id) {
        return articleRepository.findById(id);

    }

    public void deleteById(long id) {
        articleRepository.deleteById(id);

    }

    public void doModify(long id, String title, String body) {
        articleRepository.modify(id, title, body);

    }
}
