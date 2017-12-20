package com.zyz.controller;

import com.zyz.bean.Article;
import com.zyz.common.ControllerEnum;
import com.zyz.common.ControllerStatus;
import com.zyz.common.Pager;
import com.zyz.service.ArticleService;
import com.zyz.vo.ArticleVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by 娃娃鱼 on 2017/12/13.
 */
@Controller
@RequestMapping("/article")
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    @RequestMapping("addArticle")
    public String addArticle() {
        return "user/addArticle";
    }

    @PostMapping("save")
    @ResponseBody
    public ControllerStatus save(Article article) {
        ControllerStatus status = null;
        article.setCreatedTime(new Date());
        try {
            articleService.save(article);
            status = ControllerStatus.status(ControllerEnum.SUCCESS);
        } catch (RuntimeException e) {
            status = ControllerStatus.status(ControllerEnum.FAIL);
        }
        return status;
    }

    @RequestMapping("detailsPage")
    public String page() {
        return "article/article";
    }

    @RequestMapping("details")
    @ResponseBody
    public ArticleVO getById(Long articleId) {
        Object obj = articleService.getById(articleId);
        ArticleVO articleVO = (ArticleVO) obj;
        return articleVO;
    }

    @RequestMapping("allArticle")
    public String allArticle() {
        return "user/allArticle";
    }

    @RequestMapping("pager")
    @ResponseBody
    public Pager listPager(int page, int limit) {
        return articleService.listPager(page, limit);
    }

    @RequestMapping("pagerCriteria")
    @ResponseBody
    public Pager listPagerCriteria(int page, int limit, ArticleVO articleVO) {
        return articleService.listPagerCriteria(page, limit,articleVO);
    }

}
