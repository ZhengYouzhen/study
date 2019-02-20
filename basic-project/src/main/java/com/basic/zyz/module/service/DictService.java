package com.basic.zyz.module.service;

import java.util.List;

import com.basic.zyz.module.pojo.Dict;
import com.github.pagehelper.PageInfo;

/**
 * 字典管理Service
 * @author zyz
 * @version 2019-01-07
 */
public interface DictService {

	Dict get(String id);

    Dict get(Dict dict);

    List<Dict> findList(Dict dict);

    PageInfo<Dict> findPage(Dict dict);

    void save(Dict dict);

    void delete(Dict dict);

}