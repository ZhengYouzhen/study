package com.zyz.service;


import com.zyz.common.Pager;

/**
 * Created by 娃娃鱼 on 2017/12/12.
 */
public interface ArticleService extends BaseService {

    Pager listPagerCriteria(int pageNo, int pageSize, Object obj);

}
