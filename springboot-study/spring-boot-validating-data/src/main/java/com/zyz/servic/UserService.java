package com.zyz.servic;

import com.zyz.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @author 娃娃鱼
 * @date 2018/3/20 20:19
 */
public interface UserService  {

    List<User> findAll();

    User insertByUser(User user);

    User update(User user);

    User delete(Integer id);

    User findById(Integer id);

    /**
     * 获取用户分页列表
     *
     * @param pageable
     * @return
     */
    Page<User> findByPage(Pageable pageable);

}
