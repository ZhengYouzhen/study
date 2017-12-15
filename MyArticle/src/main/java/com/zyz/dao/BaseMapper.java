package com.zyz.dao;

import com.zyz.common.Pager;

import java.util.List;

/**
 * Created by 娃娃鱼 on 2017/12/12.
 */
public interface BaseMapper {

    void save(Object obj);
    void remove(Object obj);
    void removeById(Long id);
    void update(Object obj);
    Object getById(Long id);

    List<Object> listAll();
    List<Object> listPager(Pager pager);
    Long count();

}
