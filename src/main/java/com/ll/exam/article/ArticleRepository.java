package com.ll.exam.article;

import com.ll.exam.article.dto.ArticleDto;

import java.util.ArrayList;
import java.util.List;

public class ArticleRepository {
    private static Long lastId;
    private static List<ArticleDto> datum;

    static {
        datum = new ArrayList<>();
        lastId = 0L;
    }

    public long write(String title, String body) {
        long id = ++lastId;
        ArticleDto newArticleDto = new ArticleDto(id, title, body);
        datum.add(newArticleDto);

        return(id);
    }

    public List<ArticleDto> findAll() {
        return datum;
    }
}
