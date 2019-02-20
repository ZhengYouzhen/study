package com.basic.zyz;

import com.basic.zyz.common.config.Global;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BasicProjectApplicationTests {

	@Autowired
	Environment env;

	@Test
	public void contextLoads() {
		String path = System.getProperty("user.dir");
		System.out.println(path);
		System.out.println(Global.getProjectPath());

		System.out.println("配置--：" + env.getProperty("oss.url"));

		System.out.println("配置++：" + Global.getConfig("oss.url"));
//		System.out.println("配置++：" + Global.getConfig("batis.typeAliasesPackage"));
	}



}

