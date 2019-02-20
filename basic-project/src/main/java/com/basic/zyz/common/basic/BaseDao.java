package com.basic.zyz.common.basic;

import java.util.List;

/**
 * @author zyz
 * @date 2018/9/14
 */
public interface BaseDao<T> {

    /**
     * 根据id查找
     * @param id
     * @return
     */
    T get(String id);

    /**
     * 根据信息查找
     * @param t
     * @return
     */
    T queryBy(T t);

    /**
     * 保存
     * @param t
     * @return
     */
    Integer insert(T t);

    /**
     * 更新
     * @param t
     * @return
     */
    Integer update(T t);

    /**
     * 根据条件查找集合
     * @param t
     * @return
     */
    List<T> queryList(T t);
    /**
     * 删除
     * @param t
     * @return
     */
    Integer delete(T t);

}
