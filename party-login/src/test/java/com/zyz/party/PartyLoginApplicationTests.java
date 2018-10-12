package com.zyz.party;

import com.alibaba.fastjson.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PartyLoginApplicationTests {

	@Test
	public void test(){
		String str = "{\"age\":\"24\",\"name\":\"cool_summer_moon\"}";
		JSONObject  jsonObject = JSONObject.parseObject(str);
		//json对象转Map
		Map<String,Object> map = (Map<String,Object>)jsonObject;
		System.out.println("map对象是：" + map);
		Object object = map.get("age");
		System.out.println("age的值是"+object);
	}

}
