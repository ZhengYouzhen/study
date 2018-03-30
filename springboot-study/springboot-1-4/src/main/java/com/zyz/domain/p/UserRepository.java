package com.zyz.domain.p;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


/**
 * @author 娃娃鱼
 */
public interface UserRepository extends JpaRepository<User, Integer> {


}
