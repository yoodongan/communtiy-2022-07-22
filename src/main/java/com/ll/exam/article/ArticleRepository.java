package com.ll.exam.article;

import com.ll.exam.article.dto.ArticleDto;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class ArticleRepository {
    private static List<ArticleDto> datum;
    private static long lastId;

    static {
        datum = new ArrayList<>();
        lastId = 0;
        makeTestData();

    }
    private static void makeTestData() {
        IntStream.rangeClosed(1, 10).forEach(id -> {
            String title = "테스트제목%d".formatted(id);
            String body = "테스트내용%d".formatted(id);
            write(title, body);
        });
    }

    public static long write(String title, String body) {
        long id = ++lastId;   // 왜 2가 되는거지 ?
        ArticleDto newArticleDto = new ArticleDto(id, title, body);

        datum.add(newArticleDto);

        return id;
    }

    public static List<ArticleDto> findAll() {
        return datum;
    }

    public static ArticleDto findById(long id) {
        for (ArticleDto articleDto : datum) {
            if (articleDto.getId() == id) {
                return articleDto;
            }
        }
        return null;
    }

    public static void deleteById(long id) {
        ArticleDto removedArticle = findById(id);
        if( removedArticle == null ) return;
        datum.remove(removedArticle);
    }

    public static void modify(long id, String title, String body) {
        ArticleDto modifiedArticle = findById(id);
        modifiedArticle.setTitle(title);
        modifiedArticle.setBody(body);
    }
}
