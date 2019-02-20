package com.basic.zyz.module.service;

import java.util.List;

import com.basic.zyz.module.pojo.News;
import com.github.pagehelper.PageInfo;

/**
 * 咨询管理Service
 * @author zyzz
 * @version 2018-12-16
 */
public interface NewsService {

	News get(String id);

    News get(News news);

    List<News> findList(News news);

    PageInfo<News> findPage(News news);

    void save(News news);

    void delete(News news);
		
}