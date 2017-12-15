package com.zyz.dao;

import com.zyz.common.Pager;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArticleMapper extends BaseMapper{

    List<Object> listPagerCriteria(@Param("pager") Pager pager, @Param("query") Object obj);

    Long countCriteria(@Param("query") Object obj);

}