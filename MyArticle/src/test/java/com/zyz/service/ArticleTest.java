package com.zyz.service;

import com.zyz.bean.Article;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

/**
 * Created by 娃娃鱼 on 2017/12/13.
 */
public class ArticleTest extends BaseTest{

    @Autowired
    private ArticleService articleService;

    @Test
    public void testSave() {
        Article article = new Article();
        article.setTitle("第二个标题");
        article.setUserId(3L);
        article.setTypeId(1L);
        article.setFirstImg("images/face.jpg");
        article.setContent("这是第二篇测试文章，修改了userId的名字！");
        article.setCreatedTime(new Date());
        articleService.save(article);
    }

    @Test
    public void testSelect() {
        articleService.getById(1L);
    }

}
