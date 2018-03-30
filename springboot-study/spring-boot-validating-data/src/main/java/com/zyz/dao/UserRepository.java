package com.zyz.dao;

import com.zyz.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author 娃娃鱼
 * @date 2018/3/20 20:18
 */
@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

}
