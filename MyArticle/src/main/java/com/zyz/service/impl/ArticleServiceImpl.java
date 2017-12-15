package com.zyz.service.impl;

import com.zyz.common.Pager;
import com.zyz.dao.ArticleMapper;
import com.zyz.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by 娃娃鱼 on 2017/12/13.
 */
@Service
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    private ArticleMapper articleMapper;

    @Override
    public void save(Object obj) {
        articleMapper.save(obj);
    }

    @Override
    public void remove(Object obj) {
        articleMapper.remove(obj);
    }

    @Override
    public void removeById(Long id) {
        articleMapper.removeById(id);
    }

    @Override
    public void update(Object obj) {
        articleMapper.update(obj);
    }

    @Override
    public Object getById(Long id) {
        return articleMapper.getById(id);
    }

    @Override
    public List<Object> listAll() {
        return articleMapper.listAll();
    }

    @Override
    public Pager listPager(int pageNo, int pageSize) {
        Pager pager = new Pager(pageNo, pageSize);
        pager.setRows(articleMapper.listPager(pager));
        pager.setTotal(articleMapper.count());
        return pager;
    }
}
