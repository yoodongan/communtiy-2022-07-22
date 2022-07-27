package com.ll.exam.article;

import com.ll.exam.Rq;
import com.ll.exam.article.dto.ArticleDto;

import java.util.List;

public class ArticleController {
    private ArticleService articleService;

    public ArticleController() {
        articleService = new ArticleService();
    }

    public void showList(Rq rq) {
        rq.println("게시물 입니다.");
        List<ArticleDto> articleDtos = articleService.findAll();  // Service 에서 리스트로 가져온다.
        rq.setAttr("articles", articleDtos);
        rq.view("usr/article/list");

    }

    public void showWrite(Rq rq) {
        rq.view("usr/article/write");
    }

    public void doWrite(Rq rq) {
        String title = rq.getParam("title", "");
        String body = rq.getParam("body", "");

        long id = articleService.write(title, body);

        rq.println("%d번 게시물이 저장되었습니다.  ".formatted(id));
        rq.println("제목 : %s, 내용 : %s".formatted(title, body));

    }

    public void showDeatil(Rq rq) {
        long id = rq.getLongPathValueByIndex(1, 0);

        if (id == 0) {  // defaultValue 를 출력한다면,
            rq.println("번호를 입력해주세요.");
            return;
        }
        ArticleDto articleDto = articleService.findById(id);

        if (articleDto == null) {
            rq.println("해당 글이 존재하지 않습니다.");
            return;
        }

        rq.setAttr("article", articleDto);
        rq.view("usr/article/detail");
    }

    public void deleteList(Rq rq) {
        long id = rq.getLongPathValueByIndex(1, 0);

        if (id == 0) {  // defaultValue 를 출력한다면,
            rq.println("번호를 입력해주세요.");
            return;
        }
        ArticleDto articleDto = articleService.findById(id);

        if (articleDto == null) {
            rq.println("해당 글이 존재하지 않습니다.");
            return;
        }
        articleService.deleteById(id);

        rq.println("%d번 게시물이 삭제되었습니다.".formatted(id));
        rq.println("<div><a href=\"/usr/article/list/free\">리스트로 이동</a></div>".formatted(id));
    }

    public void showModifyForm(Rq rq) {
        long id = rq.getLongPathValueByIndex(1, 0);

        if (id == 0) {  // defaultValue 를 출력한다면,
            rq.println("번호를 입력해주세요.");
            return;
        }
        ArticleDto articleDto = articleService.findById(id);

        if (articleDto == null) {
            rq.println("해당 글이 존재하지 않습니다.");
            return;
        }

        rq.setAttr("article", articleDto);
        rq.view("usr/article/modify");

    }

    public void doModify(Rq rq) {
        long id = rq.getLongPathValueByIndex(1, 0);
        String title = rq.getParam("title", "");
        String body = rq.getParam("body", "");

        articleService.doModify(id, title, body);

        rq.println("<div>id : %d</div>".formatted(id));
        rq.println("<div>title : %s</div>".formatted(title));
        rq.println("<div>body : %s</div>".formatted(body));
    }
}
