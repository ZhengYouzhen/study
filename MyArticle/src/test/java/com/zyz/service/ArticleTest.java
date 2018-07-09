package com.zyz.service;

import com.zyz.bean.Article;
import com.zyz.bean.TestM;
import com.zyz.bean.Type;
import com.zyz.bean.User;
import com.zyz.common.Pager;
import com.zyz.enums.UserOrderStatus;
import com.zyz.vo.ArticleVO;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by 娃娃鱼 on 2017/12/13.
 */
public class ArticleTest extends BaseTest {

    @Autowired
    private ArticleService articleService;

    private Integer testABC = 0;
    private static int aa = 0;
    public static String[] strings = new String[10];

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

    @Test
    public void testListPage() {
        ArticleVO article = new ArticleVO();
        article.setTypeName("java");
        Pager pager = articleService.listPagerCriteria(1, 5, article);
        for (Object object : pager.getRows()) {
            ArticleVO articleVO = (ArticleVO) object;
            System.out.println(articleVO);
        }
    }

    @Test
    public void testObj() {
        Object obj = null;
        Article article = new Article();
        article.setTitle("第二个标题");
        article.setUserId(3L);
        article.setTypeId(1L);
        article.setFirstImg("images/face.jpg");
        article.setContent("这是第二篇测试文章，修改了userId的名字！");
        article.setCreatedTime(new Date());
        if (article != null) {
            obj = article;
        }
        System.out.println(obj.toString() + "----------------------------------------------------------");
        try {
//            Type type = (Type) obj;
            Article type = (Article) obj;
            System.out.println(type + "++++++++++++++++++++++++++++++++++++");
        } catch (ClassCastException e) {
            System.out.println("类型转换失败！");
            Article article1 = (Article) obj;
            System.out.println(article1.getContent() + "+++++++++++++++++++++++++");
        }

    }

    @Test
    public void testA() {
        double d1 = 1.0f;
        double d2 = 2.0f;
        double d3 = 2.0f;
        boolean b = (UserOrderStatus.PAY_NO.getCode() == 0);
        if (b) {
            System.out.println(UserOrderStatus.PAY_NO.getMsg() + "true--------------------------");
            System.out.println(UserOrderStatus.getValue(1) + "-------");
            System.out.println(UserOrderStatus.values());
        }
        System.out.println("false++++++++++++++++++++");
        BigDecimal dd = BigDecimal.valueOf((d1 + d2 + d3) / 3);
        System.out.println(dd.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
        aa += 1;
        testABC += 1;
        System.out.println(aa + "======" + testABC);
        Optional.empty();
    }

    @Test
    public void testTime() {
        SimpleDateFormat format = new SimpleDateFormat("dd");
        //获取当前月第一天：
        Calendar c = Calendar.getInstance();
        c.add(Calendar.MONTH, 0);
        c.set(Calendar.DAY_OF_MONTH, 1);//设置为1号,当前日期既为本月第一天
        String first = format.format(c.getTime());
        System.out.println("===============first:" + first);

        //获取当前月最后一天
        Calendar ca = Calendar.getInstance();
        ca.set(Calendar.DAY_OF_MONTH, ca.getActualMaximum(Calendar.DAY_OF_MONTH));
        String last = format.format(ca.getTime());
        System.out.println("===============last:" + last);
    }

    @Test
    public void testMap() {

    }

}
