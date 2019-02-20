package com.basic.zyz.common.web;

import com.basic.zyz.common.constant.RedisEnum;
import com.basic.zyz.common.utils.RedisUtil;
import com.basic.zyz.module.pojo.Dict;
import com.basic.zyz.module.pojo.User;
import com.basic.zyz.module.service.DictService;
import com.basic.zyz.module.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author zyz
 * @date 2019/1/7
 * 封装的redis方法Service
 */
@Component
public class RedisRepository {

    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private DictService dictService;

    @Autowired
    private UserService userService;

    /**
     * 输入类型和数据值匹配对应的标签名
     *
     * @param type
     * @param value
     * @return
     */
    public String getDictLabelByTypeAndValue(String type, String value) {
        String label = "";
        List<Dict> dictList = (List<Dict>) redisUtil.get(RedisEnum.REDIS_DICT.getKey() + type);
        if (dictList != null) {
            for (Dict dict : dictList) {
                if (dict.getValue().equals(value)) {
                    label = dict.getLabel();
                }
            }
        }else{
            Dict dict = new Dict();
            dict.setType(type);
            dictList = dictService.findList(dict);
            redisUtil.set(RedisEnum.REDIS_DICT.getKey() + type, dictList);
            if (dictList != null) {
                for (Dict d : dictList) {
                    if (d.getValue().equals(value)&&d.getType().equals(type)) {
                        label = d.getLabel();
                    }
                }
            }
        }
        return label;
    }

    /**
     * 根据类型查找对应的数据
     *
     * @param type
     * @return
     */
    public List<Dict> getDictLabelByType(String type) {
        List<Dict> dictList;
        Object dictRedis = redisUtil.get(RedisEnum.REDIS_DICT.getKey() + type);
        if (dictRedis != null) {
            dictList = (List<Dict>) dictRedis;
        } else {
            Dict dict = new Dict();
            dict.setType(type);
            dictList = dictService.findList(dict);
            redisUtil.set(RedisEnum.REDIS_DICT.getKey() + type, dictList);
        }
        return dictList;
    }

    public User getUserById(String id) {
        User user = userService.get(id);
        return user;
    }

}
