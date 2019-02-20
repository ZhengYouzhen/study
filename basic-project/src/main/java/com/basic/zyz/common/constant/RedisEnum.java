package com.basic.zyz.common.constant;

/**
 * @author zyz
 * @date 2019/1/3
 */
public enum RedisEnum {
    REDIS_MENU("menu-tree", 0L),
//    用户30分钟不操作，登录状态失效
    REDIS_USER("user-info:", 30 * 60L),

    REDIS_DICT("dict-list:", 0L);

    //    键值
    private String key;
    //    过期时间，如果不需要过期时间，给0L
    private long time;

    RedisEnum(String key, long time) {
        this.key = key;
        this.time = time;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }
}
