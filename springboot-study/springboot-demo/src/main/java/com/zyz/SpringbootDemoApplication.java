package com.zyz;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author zyz
 * @date 2018年7月26日
 */
@SpringBootApplication
@MapperScan("com.zyz.dao")
public class SpringbootDemoApplication {
	/**
	 * 测试测试测试
	 * @param args
	 */
	public static void main(String[] args) {
		SpringApplication.run(SpringbootDemoApplication.class, args);
	}
}
