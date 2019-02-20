package com.basic.zyz.common.constant;

/**
 * 信息说明类
 * @author zyz
 * @date 2018/12/16
 */
public enum  ResultStatus {

    SUCCESS("0000", "成功"),

    FILE_SUCCESS("0","上传成功！"),
    FILE_FAIL("1","上传失败！"),

    USER_NOT_EXIS("1000","用户不存在"),
    USER_EXIS("1001","用户已存在"),

    NO_PERMISSION("8888", "请先登录！"),

    SYSTEM_ERROR("9999","系统错误！");

//    CODE_ERROR("1111","验证码错误！")；
    /**
     * 返回码
     */
    private String code;

    /**
     * 返回结果描述
     */
    private String msg;

    ResultStatus(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

}
