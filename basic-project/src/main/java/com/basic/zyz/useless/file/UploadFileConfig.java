package com.basic.zyz.useless.file;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.MultipartConfigElement;
import java.io.File;

/**
 * @author zyz
 * @date 2019/1/9
 */
//@Configuration
public class UploadFileConfig {

    @Value("${userfiles.basedir}")
    private String uploadFolder;

//    @Bean
    MultipartConfigElement multipartConfigElement() {
        File descDir = new File(uploadFolder);
        if (!descDir.exists()) {
            // 创建目录
            descDir.mkdirs();
        }
        MultipartConfigFactory factory = new MultipartConfigFactory();
        factory.setLocation(uploadFolder);
        return factory.createMultipartConfig();
    }

}
