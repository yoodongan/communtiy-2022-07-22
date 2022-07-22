package com.ll.exam.article;

import com.ll.exam.Rq;

import java.util.ArrayList;
import java.util.List;

public class ArticleController {
    public void showList(Rq rq) {
        rq.appendBody("게시물 입니다.");
        List<ArticleDto> articleDocs = new ArrayList<>();
        articleDocs.add(new ArticleDto(5, "제목5", "내용5"));
        articleDocs.add(new ArticleDto(4, "제목4", "내용4"));
        articleDocs.add(new ArticleDto(3, "제목3", "내용3"));
        articleDocs.add(new ArticleDto(2, "제목2", "내용2"));
        articleDocs.add(new ArticleDto(1, "제목1", "내용1"));

        rq.setAttr("articles", articleDocs);
        rq.view("usr/article/list");

    }
}
