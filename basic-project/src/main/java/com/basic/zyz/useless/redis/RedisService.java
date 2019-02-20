package com.basic.zyz.useless.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.*;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.GenericToStringSerializer;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * Desc: Redis 服务封装
 * 说明，如果是springboot2.0版本以下，使用该类
 * @author zyz
 * @date 2019-01-03
 */
@Service
public class RedisService {

    @Autowired
    private RedisTemplate<Serializable, Object> redisTemplate;

    /**
     * 批量删除对应的value
     * @param keys
     */
    public void remove(final String... keys) {
        for (String key : keys) {
            remove(key);
        }
    }

    /**
     * 批量删除key
     * @param pattern
     */
    public void removePattern(final String pattern) {
        Set<Serializable> keys = redisTemplate.keys(pattern);
        if (keys.size() > 0) {
            redisTemplate.delete(keys);
        }
    }

    /**
     * 删除对应的value
     * @param key
     */
    public void remove(final String key) {
        if (exists(key)) {
            redisTemplate.delete(key);
        }
    }

    /**
     * 判断缓存中是否有对应的value
     * @param key
     * @return
     */
    public boolean exists(final String key) {
        return redisTemplate.hasKey(key);
    }

    /**
     * 读取缓存
     * @param key
     * @return
     */
    public Object get(final String key) {
        ValueOperations<Serializable, Object> operations = redisTemplate.opsForValue();
        return operations.get(key);
    }

    /**
     * 写入缓存
     * @param key
     * @param value
     * @return
     */
    public boolean set(final String key, Object value) {
        boolean result = false;
        try {
            ValueOperations<Serializable, Object> operations = redisTemplate.opsForValue();
            operations.set(key, value);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 写入缓存
     * @param key
     * @param value
     * @param expireTime
     * @return
     */
    public boolean set(final String key, Object value, Long expireTime) {
        boolean result = false;
        try {
            ValueOperations<Serializable, Object> operations = redisTemplate.opsForValue();
            operations.set(key, value);
            redisTemplate.expire(key, expireTime, TimeUnit.SECONDS);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 根据Key刷新超时时间
     * @param key
     * @param expireTime
     * @return
     */
    public boolean flushExpireTime(final String key, Long expireTime) {
        boolean result = false;
        try {
            redisTemplate.expire(key, expireTime, TimeUnit.SECONDS);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }


    public Long increase(String key, String field) {
        BoundHashOperations hashOps = redisTemplate.boundHashOps(key);
        //redisTemplate.setKeySerializer(new StringRedisSerializer(StandardCharsets.UTF_8));
        //redisTemplate.setValueSerializer(new StringRedisSerializer());
        //redisTemplate.setHashKeySerializer(new StringRedisSerializer(StandardCharsets.UTF_8));
        redisTemplate.setHashValueSerializer(new GenericToStringSerializer(Long.class));
        if (exists(key, field)) {
            return hashOps.increment(field, 1L);
        } else {
            hashOps.putIfAbsent(field, 1);
            return 1L;
        }
    }

	public Long increaseRandom(String key, String field, Long step) {
		BoundHashOperations hashOps = redisTemplate.boundHashOps(key);
		redisTemplate.setHashValueSerializer(new GenericToStringSerializer(Long.class));
		if (exists(key, field)) {
			return hashOps.increment(field, step);
		} else {
			hashOps.putIfAbsent(field, 1);
			return 1L;
		}
	}

    public Long decrease(String key, String field) {
        BoundHashOperations hashOps = redisTemplate.boundHashOps(key);
        redisTemplate.setHashValueSerializer(new GenericToStringSerializer(Long.class));
        if (exists(key, field)) {
            long count = hashOps.increment(field, -1L);
            if (count == 0) {
                deleteField(key, field);
                return 0L;
            } else {
                return count;
            }
        }
        return 0L;
    }

    public void deleteField(String key, String field) {
        BoundHashOperations hashOps = redisTemplate.boundHashOps(key);
        hashOps.delete(field);
    }

    public void batchDeleteField(String key, String... field) {
        BoundHashOperations hashOps = redisTemplate.boundHashOps(key);
        hashOps.delete(field);
    }

    public Set<String> getFields(String key) {
        BoundHashOperations hashOps = redisTemplate.boundHashOps(key);
        return hashOps.keys();
    }

    public boolean exists(String key, String field) {
        BoundHashOperations hashOps = redisTemplate.boundHashOps(key);
        return hashOps.hasKey(field);
    }

    public Object getValueByKeyAndField(String key, String field) {
        BoundHashOperations hashOps = redisTemplate.boundHashOps(key);
        return hashOps.get(field);
    }
    
    public Object getObjectValueByKeyAndField(String key, String field) {
        BoundHashOperations hashOps = redisTemplate.boundHashOps(key);
        redisTemplate.setHashValueSerializer(new GenericJackson2JsonRedisSerializer());
        return hashOps.get(field);
    }
    
    public Map<Serializable, Object> getEntries(String key) {
        BoundHashOperations hashOps = redisTemplate.boundHashOps(key);
        //redisTemplate.setHashKeySerializer(new GenericToStringSerializer(String.class));
        redisTemplate.setHashValueSerializer(new GenericToStringSerializer(Long.class));
        return hashOps.entries();
    }
    
    public Map<Serializable, Object> getStringEntries(String key) {
        BoundHashOperations hashOps = redisTemplate.boundHashOps(key);
        redisTemplate.setHashValueSerializer(new GenericToStringSerializer(String.class));
        return hashOps.entries();
    }
    
    public Map<Serializable, Object> getObjectEntries(String key) {
        BoundHashOperations hashOps = redisTemplate.boundHashOps(key);
        redisTemplate.setHashValueSerializer(new GenericJackson2JsonRedisSerializer());
        return hashOps.entries();
    }

    public void put(String key, String field, Long value) {
        BoundHashOperations hashOps = redisTemplate.boundHashOps(key);
        redisTemplate.setHashValueSerializer(new GenericToStringSerializer(Long.class));
        hashOps.put(field, value);
    }
    
    public void put(String key, String field, String value) {
        BoundHashOperations hashOps = redisTemplate.boundHashOps(key);
        redisTemplate.setHashValueSerializer(new GenericToStringSerializer(String.class));
        hashOps.put(field, value);
    }
    
    public void put(String key, String field, Object value) {
        BoundHashOperations hashOps = redisTemplate.boundHashOps(key);
        hashOps.put(field, value);
    }
    
    public void putObject(String key, String field, Object value) {
        BoundHashOperations<Serializable, String, Object> hashOps = redisTemplate.boundHashOps(key);
        redisTemplate.setHashValueSerializer(new GenericJackson2JsonRedisSerializer());
        hashOps.put(field, value);
    }
    
    /*实现简单的redis zset*/
    public void putZset(String key, Object value, double score) {
    	BoundZSetOperations<Serializable, Object> setOps = redisTemplate.boundZSetOps(key);
    	setOps.add(value, score);
    }
    public Set<Object> getZset(String key) {
    	BoundZSetOperations<Serializable, Object> setOps = redisTemplate.boundZSetOps(key);
    	return setOps.range(0, -1);
    }
    
    /*实现简单的redis list*/
    public void putList(String key, Object value) {
    	BoundListOperations<Serializable, Object> listOps = redisTemplate.boundListOps(key);
    	redisTemplate.setHashValueSerializer(new GenericJackson2JsonRedisSerializer());
    	listOps.rightPush(value);
    }
    public List<Object> getList(String key) {
    	BoundListOperations<Serializable, Object> listOps = redisTemplate.boundListOps(key);
    	redisTemplate.setHashValueSerializer(new GenericJackson2JsonRedisSerializer());
    	return listOps.range(0, -1);
    }

}
