package com.zyz.service.impl;

import com.zyz.common.Pager;
import com.zyz.dao.TypeMapper;
import com.zyz.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by 娃娃鱼 on 2017/12/13.
 */
@Service
public class TypeServiceImpl implements TypeService {

    @Autowired
    private TypeMapper typeMapper;

    @Override
    public void save(Object obj) {
        typeMapper.save(obj);
    }

    @Override
    public void remove(Object obj) {
typeMapper.remove(obj);
    }

    @Override
    public void removeById(Long id) {
typeMapper.removeById(id);
    }

    @Override
    public void update(Object obj) {
typeMapper.update(obj);
    }

    @Override
    public Object getById(Long id) {
        return typeMapper.getById(id);
    }

    @Override
    public List<Object> listAll() {
        return typeMapper.listAll();
    }

    @Override
    public Pager listPager(int pageNo, int pageSize) {
        return null;
    }
}
