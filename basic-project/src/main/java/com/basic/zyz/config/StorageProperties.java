package com.basic.zyz.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("storage")
public class StorageProperties {

    /**
     * 文件存储在此文件下，此文件创建项目当前所在的根路径。
     * windows：项目路径为：D:\XXXX\basic-project，则文件夹创建在：D:\basicfiles
     * linux：jar包存放在：/home/basic-project，文件创建：/basicfiles
     */
    private String location = "/basicfiles";

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

}
