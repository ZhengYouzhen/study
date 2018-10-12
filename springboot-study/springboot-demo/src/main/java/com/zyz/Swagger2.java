package com.zyz;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


/**
 *  @author zyz
 *  @date 2018/7/26
 *  此类作为配置
 *  @Configuration
 *  声明swagger在配置中swagger.enable=true时启动swagger，自定义配置内容
 *   @ConditionalOnProperty(prefix = "swagger",value = {"enable"},havingValue = "true")
 *  swagger配置
 *  @EnableSwagger2
 */
@Configuration
@ConditionalOnProperty(prefix = "swagger",value = {"enable"},havingValue = "true")
@EnableSwagger2
public class Swagger2 {

    @Value("${spring.application.name}")
    private String title;

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
//                swagger覆盖的范围
                .apis(RequestHandlerSelectors.basePackage("com.zyz.controller"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        Contact contact=new Contact(title,
                "https://www.baidu.com","1006747577@qq.com");

        return new ApiInfoBuilder()
//                标题
                .title(title + " RESTful APIs")
//                详情
                .description(title + " RESTful API详情!")
//                创建人信息
                .contact(contact)
//                版本号
                .version("1.0.0")
                .build();
    }

}
