package com.zyz.service;

import com.zyz.common.Pager;

import java.util.List;

/**
 * Created by 娃娃鱼 on 2017/12/12.
 */
public interface BaseService {

    void save(Object obj);
    void remove(Object obj);
    void removeById(Long id);
    void update(Object obj);
    Object getById(Long id);

    List<Object> listAll();
    Pager listPager(int pageNo, int pageSize);
    
}
