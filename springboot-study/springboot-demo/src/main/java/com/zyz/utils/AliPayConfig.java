package com.zyz.utils;

/**
 * @author zyz
 * @date 2018/7/27
 */
public class AliPayConfig {
    /**
     * 应用appId
     */
    public static String APP_ID = "替换为自己的";
    /**
     * 合作身份者ID，以2088开头由16位纯数字组成的字符串20889111420456465
     */
    public static String partner = "替换为自己的";
    // 私钥
    public static String APP_PRIVATE_KEY = "替换为自己的";
    /**
     * 支付宝的公钥
     * 该公钥对应开放平台密钥的支付宝公钥，
     */
    public static String ALIPAY_PUBLIC_KEY = "替换为自己的";
    /**
     * 这个公钥对应合作伙伴密钥的支付宝公钥，历史接口使用该公钥
     */
    public static String ali_public_key1 = "替换为自己的";
    /**
     * 调试用，创建TXT日志文件夹路径
     */

    public static String log_path = "/usr/local/tomcat/logs";
    /**
     * 字符编码
     */
    public static String CHARSET = "utf-8";
    /**
     * //签名方式
     */
    public static String SIGN_TYPE = "RSA";


}
