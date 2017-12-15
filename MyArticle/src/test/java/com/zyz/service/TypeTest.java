package com.zyz.service;

import com.zyz.bean.Type;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by 娃娃鱼 on 2017/12/13.
 */
public class TypeTest extends BaseTest {

    @Autowired
    private TypeService typeService;

    @Test
    public void testSave() {
        Type type = new Type();
        type.setTypeName("js");
        type.setDes("javascript相关的文章");
        typeService.save(type);
    }

    @Test
    public void testAll() {
        List<Object> objects = typeService.listAll();
        for (Object obj : objects) {
            Type type = (Type) obj;
            System.out.println(type);
        }
    }

}
