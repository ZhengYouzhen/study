package com.zyz.domain;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author 娃娃鱼
 * @date 2018/3/28 8:32
 */
@CacheConfig(cacheNames = "users")
public interface UserRepository extends JpaRepository<User, Integer> {

    @Cacheable(key = "#p0", condition = "#p0.length() < 10")
    User findByName(String name);

}
