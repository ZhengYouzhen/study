package com.basic.zyz;

import com.basic.zyz.common.config.CustomDialect;
import com.basic.zyz.common.config.WorkFocusDialect;
import com.basic.zyz.common.file.service.StorageService;
import com.basic.zyz.config.StorageProperties;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * 扫码mybatis 对应的到文件路径
 * MapperScan("com.basic.zyz.module.dao")
 * springboot启动注解
 * SpringBootApplication
 * 扫描这些包下可以使用注入
 * ComponentScan(value = "com.basic.zyz",lazyInit = true)
 * 识别包下的过滤器，拦截器，配置等等
 * ServletComponentScan("com.basic.zyz")
 * 开启事物管理，在相应的类上加事物注解即可
 * EnableTransactionManagement
 * spring文件上传配置注入
 * EnableConfigurationProperties(StorageProperties.class)
 * @author zyz
 */
@MapperScan("com.basic.zyz.module.dao")
@SpringBootApplication
@ComponentScan(value = "com.basic.zyz",lazyInit = true)
@ServletComponentScan("com.basic.zyz")
@EnableTransactionManagement
@EnableConfigurationProperties(StorageProperties.class)
public class BasicProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(BasicProjectApplication.class, args);
		StringBuilder sb = new StringBuilder();
		sb.append("\r\n======================================================================\r\n");
		sb.append("\r\n    启动成功  - Powered By ZYZ\r\n");
		sb.append("\r\n======================================================================\r\n");
		System.out.println(sb.toString());
	}

	/**
	 * 创建文件上传基础目录
	 */
	@Bean
	CommandLineRunner init(StorageService storageService) {
		return (args) -> storageService.init();
	}

	/**
	 * thymeleaf自定义语法
	 * @return
	 */
	@Bean
	public CustomDialect getDialect() {
		return new CustomDialect();
	}

	/**
	 * thymeleaf自定义使用对象
	 * @return
	 */
	@Bean
	@ConditionalOnMissingBean
	public WorkFocusDialect wlfDialect() {
		return new WorkFocusDialect();
	}
}

