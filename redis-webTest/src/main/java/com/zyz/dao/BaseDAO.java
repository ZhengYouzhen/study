package com.zyz.dao;

import com.zyz.common.Pager;

import java.util.List;

/**
 * @author 娃娃鱼
 */
public interface BaseDAO {

    void save(Object obj);
    void remove(Object obj);
    void removeById(Long id);
    void update(Object obj);

    Object getById(Long id);
    List<Object> listAll();
    List<Object> listPager(Pager pager);
    Long count();

    List<Object> listPagerCriteria(Pager pager, Object obj);
    Long countCriteria(Object obj);


}